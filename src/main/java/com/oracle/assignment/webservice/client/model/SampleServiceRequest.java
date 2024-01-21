package com.oracle.assignment.webservice.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SampleServiceRequest {
    @JsonProperty("sampleservicerq")
    private SampleServiceRq sampleServiceRq;
}
