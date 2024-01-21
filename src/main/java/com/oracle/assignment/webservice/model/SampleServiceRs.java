package com.oracle.assignment.webservice.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="sampleservicers")
public class SampleServiceRs {
    @XmlElement(name="error_code")
    private String errorCode;

    @XmlElement(name="error_msg")
    private String errorMsg;

    @XmlElement(name="trx_id")
    private String trxId;
}
