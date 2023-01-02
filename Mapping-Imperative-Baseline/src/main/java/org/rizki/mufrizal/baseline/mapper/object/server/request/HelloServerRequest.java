package org.rizki.mufrizal.baseline.mapper.object.server.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HelloServerRequest implements Serializable {

    @JsonProperty("ReferenceNumber")
    private String referenceNumber;
    @JsonProperty("Message")
    private String message;
}