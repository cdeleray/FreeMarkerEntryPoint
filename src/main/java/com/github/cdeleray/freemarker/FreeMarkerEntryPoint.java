/*
 * MIT License
 *
 * Copyright (c) 2020 Christophe Deleray
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.cdeleray.freemarker;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;
import static freemarker.template.TemplateExceptionHandler.RETHROW_HANDLER;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Locale.FRANCE;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * A {@code FreeMarkerEntryPoint} object represents a (very!) simple 
 * <a href="http://freemarker.org/">Freemarker</a> entry point that allows
 * to create documents from templates. 
 *
 * @author Christophe Deleray
 */
@FunctionalInterface
public interface FreeMarkerEntryPoint {
  /**
   * Dumps the bytes content, produced from the given template file and data 
   * model, out the given character output stream.
   * 
   * @param templatePath the path of a template file resolved from the classpath
   * @param model the data model
   * @param locale the locale to use by FreeMarker
   * @param encoding the encoding to use by FreeMarker
   * @param out a character output stream where the data is dumped out
   * @throws FreeMarkerEntryPointException if an error occurs when producing the content
   */
  void dumpContent(String templatePath, Object model, Locale locale, String encoding, Writer out);

  /**
   * Returns the content produced from the given template file and data model.
   * 
   * @implSpec
   * The default implementation returns the String built from a {@link 
   * StringWriter} instance passed as argument of the {@link #dumpContent(String, Object, Locale, String, Writer)}
   * method.
   * 
   * @param templatePath the path of a template file resolved from the classpath
   * @param model the data model
   * @param locale the locale to use by FreeMarker
   * @param encoding the encoding to use by FreeMarker
   * @return the content produced from the given template file and data model
   * @throws FreeMarkerEntryPointException if an error occurs when producing the content
   */
  default String getContent(String templatePath, Object model, Locale locale, String encoding) {
    StringWriter out = new StringWriter(1 << 21); //~2Mo
    dumpContent(templatePath, model, locale, encoding, out);
    return out.toString();
  }

  /**
   * Dumps the content, produced from the given template file and data model, 
   * out the given character output stream.
   * 
   * @implSpec
   * The default implementation calls the {@link #dumpContent(String, Object, Locale, String, Writer)}
   * method with {@code name}, {@code model}, {@link Locale#FRANCE Locale.FRANCE}, 
   * {@code UTF-8} and {@code out} as arguments.
   * 
   * @param name the name of a template file
   * @param model the data model
   * @param out a character output stream where the data is dumped out
   * @throws FreeMarkerEntryPointException if an error occurs when producing the content
   */
  default void dumpContent(String name, Object model, Writer out) {
    dumpContent(name, model, FRANCE, UTF_8.name(), out);
  }
  
  /**
   * Returns the content produced from the given template file and data model.
   * 
   * @implSpec
   * The default implementation calls the {@link #getContent(String, Object, Locale, String)}
   * method with {@code templatePath}, {@code model}, {@link Locale#FRANCE 
   * Locale.FRANCE} and {@code UTF-8} as arguments. 
   * 
   * @param templatePath the path of a template file resolved from the classpath
   * @param model the data model
   * @return the content produced from the given template file and data model
   * @throws FreeMarkerEntryPointException if an error occurs when producing the content
   */
  default String getContent(String templatePath, Object model) {
    return getContent(templatePath, model, FRANCE, UTF_8.name());
  }

  /**
   * Returns a new {@code FreeMarkerEntryPoint} built from the given 
   * FreeMarker configuration.
   *
   * @param configuration the FreeMarker configuration
   * @return a new {@code FreeMarkerEntryPoint} built from the given 
   * FreeMarker configuration
   */
  static FreeMarkerEntryPoint of(Configuration configuration) {
    return (templatePath, model, locale, encoding, out) -> {
      Logger logger = Logger.getLogger(FreeMarkerEntryPoint.class.getName());
      
      try {
        Template template = configuration.getTemplate(templatePath, locale, encoding);
        template.setOutputEncoding(encoding);
        template.process(model, out);
        
        logger.fine(out::toString);
      }
      catch(IOException | TemplateException e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
        throw new FreeMarkerEntryPointException(e);
      }
    };
  }
  
  /**
   * Returns a new {@code FreeMarkerEntryPoint} built with a pre-built 
   * FreeMarker configuration which loads the template files from their 
   * location in the classpath.
   * method.
   * <p>
   * The {@code FreeMarkerEntryPoint} instance returned by this method
   * would cover most of the needs. It is also <em>thread-safe</em> 
   * by design for the underlying {@link Configuration} object is not
   * reachable. 
   * 
   * @return a new {@code FreeMarkerEntryPoint} built from a pre-built 
   * FreeMarker configuration that would cover most of needs
   */
  static FreeMarkerEntryPoint defaultEntryPoint() {
    Configuration configuration = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    configuration.setTemplateLoader(new ClassTemplateLoader(FreeMarkerEntryPoint.class, "/"));
    configuration.setNumberFormat("####");
    configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
    configuration.setTemplateExceptionHandler(RETHROW_HANDLER);
    configuration.setLogTemplateExceptions(false);   
    
    return of(configuration);
  }

  /**
   * Returns a new {@code FreeMarkerEntryPoint} built with a pre-built 
   * FreeMarker configuration which loads the template files from the 
   * package location of the given class by using its {@link Class#getResource(String)}
   * method.
   * <p>
   * The {@code FreeMarkerEntryPoint} instance returned by this method
   * would cover most of the needs. It is also <em>thread-safe</em> 
   * by design for the underlying {@link Configuration} object is not
   * reachable. 
   * 
   * @param resourceLoaderClass a class whose {@link Class#getResource(String)} 
   * method will be used to load the templates from its package location
   * @return a new {@code FreeMarkerEntryPoint} built from a pre-built 
   * FreeMarker configuration that would cover most of needs
   */
  static FreeMarkerEntryPoint defaultEntryPoint(Class<?> resourceLoaderClass) {
    Configuration configuration = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    configuration.setTemplateLoader(new ClassTemplateLoader(resourceLoaderClass, ""));
    configuration.setNumberFormat("####");
    configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
    configuration.setTemplateExceptionHandler(RETHROW_HANDLER);
    configuration.setLogTemplateExceptions(false);   
    
    return of(configuration);
  }
}
