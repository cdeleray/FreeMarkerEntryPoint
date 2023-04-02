/*
 * MIT License
 *
 * Copyright (c) 2023 Christophe Deleray
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

import java.io.Serial;

/**
 * A {@code FreeMarkerEntryPointException} object represents an exception
 * thrown by the methods defined by the {@link FreeMarkerEntryPoint} interface
 * whenever an IO error or a FreeMarker-based error occurs.
 *
 * @author Christophe Deleray
 */
public class FreeMarkerEntryPointException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4396240703314737350L;

    /**
     * Creates a new instance of the {@code FreeMarkerEntryPointException} class
     * initialized with the given root cause.
     *
     * @param cause the root cause of this exception
     */
    public FreeMarkerEntryPointException(Throwable cause) {
        super(cause);
    }

}
