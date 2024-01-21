package com.oracle.assignment.webservice.object;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SampleServiceOutputDto {
    private String errorCode;
    private String errorMsg;
    private String trxId;
}
