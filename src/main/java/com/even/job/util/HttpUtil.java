package com.even.job.util;

import ch.qos.logback.core.util.CloseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * Created by yidadi on 17-5-11.
 */
public class HttpUtil {
    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpResponse httpGetHttpResponse(String url) throws IOException {
        //get请求返回结果
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            return response;
        } catch (IOException e) {
            throw e;
        } finally {
            request.releaseConnection();
            CloseUtil.closeQuietly(client);
        }
    }
}
