package org.rizki.mufrizal.baseline;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rizki.mufrizal.baseline.helper.JsonHelper;
import org.rizki.mufrizal.baseline.mapper.object.client.response.HelloClientResponse;
import org.rizki.mufrizal.baseline.mapper.object.server.request.HelloServerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MappingImperativeBaselineApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private static WireMockServer wireMockServer;
    private JsonHelper jsonHelper;

    @BeforeAll
    public static void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8181));
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @BeforeEach
    @SneakyThrows
    public void setupMock() {
        jsonHelper = JsonHelper.getInstance();
        HelloClientResponse helloClientResponse = HelloClientResponse.builder()
                .message("Hello Test")
                .referenceNumber(UUID.randomUUID().toString())
                .code("200")
                .description("Success")
                .hash(UUID.randomUUID().toString())
                .build();

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/simulator/sayHello"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonHelper.objectMapper.writeValueAsString(helloClientResponse))));
    }

    @AfterAll
    public static void shutdown() {
        wireMockServer.stop();
    }

    @Test
    @SneakyThrows
    void sayHello() {
        HelloServerRequest helloServerRequest = new HelloServerRequest();
        helloServerRequest.setMessage("Hello Test");
        helloServerRequest.setReferenceNumber(UUID.randomUUID().toString());

        mockMvc.perform(post("/api/v1/hello")
                        .content(jsonHelper.objectMapper.writeValueAsString(helloServerRequest))
                        .header("Accept", "application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Message", is("Hello Test")));
    }
}
