package com.oracle.assignment.webservice.service;

import com.oracle.assignment.webservice.object.SampleServiceInputDto;
import com.oracle.assignment.webservice.object.SampleServiceOutputDto;

public interface SampleService {
    SampleServiceOutputDto executeSample(SampleServiceInputDto input);
}
