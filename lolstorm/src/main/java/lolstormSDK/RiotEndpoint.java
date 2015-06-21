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

import retrofit.Endpoint;

public class RiotEndpoint implements Endpoint {
    private static final String BASE1 = "https://";
    private static final String BASE2 = ".api.pvp.net";

    private String url;
    private String region;

    private static RiotEndpoint endpoint;

    public static RiotEndpoint getInstance() {
        if (null == endpoint) {
            endpoint = new RiotEndpoint();
            endpoint.setRegion("na");
        }

        return endpoint;
    }
    public void setRegion(String region) {
        url = BASE1 + region + BASE2;
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String getName() {
        return "default";
    }

    @Override
    public String getUrl() {
        if (url == null) throw new IllegalStateException("Error.");
        return url;
    }
}