package org.rizki.mufrizal.baseline.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonHelper {

    public ObjectMapper objectMapper;
    private static JsonHelper instance = null;

    public JsonHelper() {
        objectMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static JsonHelper getInstance() {
        if (instance == null) {
            instance = new JsonHelper();
        }
        return instance;
    }

}
