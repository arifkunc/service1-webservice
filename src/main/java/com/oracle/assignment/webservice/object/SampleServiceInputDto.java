package com.oracle.assignment.webservice.object;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SampleServiceInputDto {
    private String serviceId;
    private String orderType;
    private String type;
    private String trxId;
}
