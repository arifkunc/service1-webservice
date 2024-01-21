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
@XmlRootElement(name="sampleservicerq")
public class SampleServiceRq {
    @XmlElement(name="service_id", required = true)
    private String serviceId;

    @XmlElement(name="order_type", required = true)
    private String orderType;

    @XmlElement(name="type")
    private String type;

    @XmlElement(name="trx_id")
    private String trxId;
}
