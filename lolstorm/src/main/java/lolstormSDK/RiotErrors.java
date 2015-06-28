/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Christopher C. Thi
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

package lolstormSDK;

public class RiotErrors {

    public static final int HTTP_400 = 400;
    public static final int HTTP_401 = 401;
    public static final int HTTP_404 = 404;
    public static final int HTTP_429 = 429;
    public static final int HTTP_500 = 500;
    public static final int HTTP_503 = 503;

    public static class RiotConnectionException extends Exception {
        public RiotConnectionException() {}
    }

    public static class RiotDataNotFoundException extends Exception {
        public RiotDataNotFoundException() {}
    }

    public static class RiotServerFailureException extends Exception {
        public RiotServerFailureException() {}
    }

    public static class RiotApiLimitException extends Exception {
        public RiotApiLimitException() {}
    }

    public static class RiotGenericFailureException extends Exception {
        public RiotGenericFailureException() {}
    }
}
