package com.oracle.assignment.webservice.endpoint;

import com.oracle.assignment.webservice.model.SampleServiceRq;
import com.oracle.assignment.webservice.model.SampleServiceRs;
import com.oracle.assignment.webservice.object.SampleServiceInputDto;
import com.oracle.assignment.webservice.object.SampleServiceOutputDto;
import com.oracle.assignment.webservice.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SampleServiceEndpoint {

    private static final String NAMESPACE_URI = "http://www.oracle.com/external/services/sampleservice/request/v1.0";

    private SampleService sampleService;

    @Autowired
    public SampleServiceEndpoint(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sampleservicerq")
    @ResponsePayload
    public SampleServiceRs getSampleService(@RequestPayload SampleServiceRq request) {

        SampleServiceInputDto inputDto = SampleServiceInputDto.builder()
                .serviceId(request.getServiceId())
                .orderType(request.getOrderType())
                .type(request.getType())
                .trxId(request.getTrxId())
                .build();

        SampleServiceOutputDto outputDto = sampleService.executeSample(inputDto);

        return SampleServiceRs.builder()
                .errorCode(outputDto.getErrorCode())
                .errorMsg(outputDto.getErrorMsg())
                .trxId(outputDto.getTrxId())
                .build();
    }

}
