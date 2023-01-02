package org.rizki.mufrizal.baseline.restclient;

import org.rizki.mufrizal.baseline.configuration.HttpComponentExecution;
import org.rizki.mufrizal.baseline.domain.EndPoint;
import org.rizki.mufrizal.baseline.helper.LoggingHelper;
import org.rizki.mufrizal.baseline.mapper.HelloMapper;
import org.rizki.mufrizal.baseline.mapper.object.client.request.HelloClientRequest;
import org.rizki.mufrizal.baseline.mapper.object.client.response.HelloClientResponse;
import org.rizki.mufrizal.baseline.mapper.object.server.request.HelloServerRequest;
import org.rizki.mufrizal.baseline.mapper.object.server.response.GeneralServerResponse;
import org.rizki.mufrizal.baseline.service.EndPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class HelloRestClient {
    @Autowired
    private HelloMapper helloMapper;

    @Autowired
    private Environment environment;

    @Autowired
    private EndPointService endPointService;

    public GeneralServerResponse sayHello(HelloServerRequest helloServerRequest) {
        String backend = environment.getRequiredProperty("mapping.hello.backend");
        String backendFunction = environment.getRequiredProperty("mapping.hello.backend-function");
        EndPoint endPoint = endPointService.findByBackendAndBackendFunction(backend, backendFunction);

        HelloClientRequest helloClientRequest = helloMapper.toHelloClientRequest(helloServerRequest);
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes()));
            HttpComponentExecution<HelloClientResponse> httpComponentExecution = new HttpComponentExecution<>(endPoint.getMethod().toString(), endPoint.getUrl());
            HelloClientResponse helloClientResponse = httpComponentExecution.execute(endPoint.getConnectTimeout(), endPoint.getTimeout(), helloClientRequest, headers, HelloClientResponse.class);
            return helloMapper.toHelloServerResponse(backend, helloClientResponse);
        } catch (Exception e) {
            LoggingHelper.log(e);
        }
        return null;
    }
}
