package com.github.cdeleray.freemarker;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

/**
 * Test class for {@link FreeMarkerEntryPoint}.
 *
 * @author Christophe Deleray
 */
@Test
public class FreeMarkerEntryPointTest {
  private FreeMarkerEntryPoint freeMarker;

  @BeforeMethod
  void beforeMethod() {
    freeMarker = FreeMarkerEntryPoint.defaultEntryPoint(getClass());
  }

  public void testDumpContent() {
    StringWriter output = new StringWriter();
    Map<String,Object> model = Map.of("name", "Christophe");
    freeMarker.dumpContent("template.ftl", model, Locale.FRANCE, "UTF-8", output);
    Assert.assertEquals(output.toString(), "Hello Christophe!");
  }
}
