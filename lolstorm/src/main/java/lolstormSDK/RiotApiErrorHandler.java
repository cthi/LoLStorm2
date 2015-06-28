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

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class RiotApiErrorHandler implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {

        if (RetrofitError.Kind.NETWORK == cause.getKind()) {
            return new RiotErrors.RiotConnectionException();
        } else if (RetrofitError.Kind.HTTP == cause.getKind()) {
            switch (cause.getResponse().getStatus()) {
                case RiotErrors.HTTP_400:
                    return new RiotErrors.RiotGenericFailureException();
                case RiotErrors.HTTP_401:
                    return new RiotErrors.RiotGenericFailureException();
                case RiotErrors.HTTP_404:
                    return new RiotErrors.RiotDataNotFoundException();
                case RiotErrors.HTTP_429:
                    return new RiotErrors.RiotApiLimitException();
                case RiotErrors.HTTP_500:
                    return new RiotErrors.RiotServerFailureException();
                case RiotErrors.HTTP_503:
                    return new RiotErrors.RiotServerFailureException();
                default:
                    return cause;
            }
        }
        return cause;
    }
}
