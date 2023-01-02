package org.rizki.mufrizal.baseline.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.rizki.mufrizal.baseline.helper.JsonHelper;
import org.rizki.mufrizal.baseline.helper.LoggingHelper;

import java.net.URI;
import java.util.Map;

@Log4j2
public class HttpComponentExecution<T> extends HttpUriRequestBase {

    public HttpComponentExecution(String method, String url) {
        super(method, URI.create(url));
    }

    public T execute(int connectTimeout, int activeTimeout, Object requestBody, Map<String, String> headers, Class<T> tClass) throws Exception {
        headers.entrySet().parallelStream().forEach(header -> this.addHeader(header.getKey(), header.getValue()));
        JsonHelper jsonHelper = JsonHelper.getInstance();
        if (requestBody != null) {
            try {
                this.setEntity(new StringEntity(jsonHelper.objectMapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));
            } catch (JsonProcessingException e) {
                LoggingHelper.log(e);
                throw new RuntimeException(e);
            }
        }

        log.info("Request Url {}", this.getUri());
        log.info("Request Method {}", this.getMethod());
        log.info("Request Header {}", jsonHelper.objectMapper.writeValueAsString(this.getHeaders()));
        log.info("Request Body {}", jsonHelper.objectMapper.writeValueAsString(requestBody));

        try (CloseableHttpClient closeableHttpClient = HttpComponentConfiguration.config(connectTimeout, activeTimeout)) {
            long t1 = System.nanoTime();
            return closeableHttpClient.execute(this, response -> {
                long t2 = System.nanoTime();
                String responseBody = EntityUtils.toString(response.getEntity());
                T tObject = jsonHelper.objectMapper.readValue(responseBody, tClass);

                log.info(String.format("Received response for %s in %.1fms", this.getRequestUri(), (t2 - t1) / 1e6d));
                log.info("Response Header {}", jsonHelper.objectMapper.writeValueAsString(response.getHeaders()));
                log.info("Response Http Status {}", response.getCode());
                log.info("Response Http Message {}", response.getReasonPhrase());
                log.info("Response Http Version {}", response.getVersion());
                log.info("Response Body {}", responseBody);
                log.info("Response Body Object {}", jsonHelper.objectMapper.writeValueAsString(tObject));
                return tObject;
            });
        } catch (Exception e) {
            LoggingHelper.log(e);
            throw new RuntimeException(e);
        }
    }
}