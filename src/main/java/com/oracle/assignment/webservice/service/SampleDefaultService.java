package com.oracle.assignment.webservice.service;

import com.oracle.assignment.webservice.client.model.SampleServiceRequest;
import com.oracle.assignment.webservice.client.model.SampleServiceResponse;
import com.oracle.assignment.webservice.client.model.SampleServiceRq;
import com.oracle.assignment.webservice.object.SampleServiceInputDto;
import com.oracle.assignment.webservice.object.SampleServiceOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SampleDefaultService implements SampleService{
    private String urlRest;
    private RestTemplate restTemplate;

    @Autowired
    public SampleDefaultService(@Value("${service2.url.sample-service}") String urlRest,
                                RestTemplateBuilder restTemplateBuilder) {
        this.urlRest = urlRest;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public SampleServiceOutputDto executeSample(SampleServiceInputDto input) {
        SampleServiceRequest sampleServiceRequest = SampleServiceRequest.builder()
                .sampleServiceRq(
                        SampleServiceRq.builder()
                                .serviceId(input.getServiceId())
                                .orderType(input.getOrderType())
                                .type(input.getType())
                                .trxId(input.getTrxId())
                                .build()
                )
                .build();

        SampleServiceResponse sampleServiceResponse = restTemplate.postForObject(urlRest, sampleServiceRequest, SampleServiceResponse.class);

        return SampleServiceOutputDto.builder()
                .errorCode(sampleServiceResponse.getSampleServiceRs().getErrorCode())
                .errorMsg(sampleServiceResponse.getSampleServiceRs().getErrorMsg())
                .trxId(sampleServiceResponse.getSampleServiceRs().getTrxId())
                .build();
    }
}
