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

/**
 * A {@code FreeMarkerEntryPointException} object represents an exception
 * thrown by the methods defined by the {@link FreeMarkerEntryPoint} interface
 * whenever an IO error or a FreeMarker-based error occurs.
 * 
 * @author Christophe Deleray
 */
public class FreeMarkerEntryPointException extends RuntimeException {
  private static final long serialVersionUID = 4396240703314737350L;

  /**
   * Creates a new instance of the {@code FreeMarkerEntryPointException} class
   * initialized with no detail message nor root cause.
   */
  public FreeMarkerEntryPointException() {
  }

  /**
   * Creates a new instance of the {@code FreeMarkerEntryPointException} class
   * initialized with the given detail message.
   * 
   * @param message the detail message of this exception
   */
  public FreeMarkerEntryPointException(String message) {
    super(message);
  }

  /**
   * Creates a new instance of the {@code FreeMarkerEntryPointException} class
   * initialized with the given root cause.
   * 
   * @param cause the root cause of this exception
   */
  public FreeMarkerEntryPointException(Throwable cause) {
    super(cause);
  }

  /**
   * Creates a new instance of the {@code FreeMarkerEntryPointException} class
   * initialized with the given detail message and root cause.
   * 
   * @param message the detail message of this exception
   * @param cause the root cause of this exception
   */
  public FreeMarkerEntryPointException(String message, Throwable cause) {
    super(message, cause);
  }
}
