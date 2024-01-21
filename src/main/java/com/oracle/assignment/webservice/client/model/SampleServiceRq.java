package com.oracle.assignment.webservice.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SampleServiceRq {
    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("order_type")
    private String orderType;

    @JsonProperty("type")
    private String type;

    @JsonProperty("trx_id")
    private String trxId;
}
