package com.ms.appservice.service.impl;

import com.ms.appservice.model.Sample;
import com.ms.appservice.service.SampleService;
import org.springframework.stereotype.Service;

@Service("sampleService")
public class SampleServiceImpl extends BaseServiceImpl<Sample> implements SampleService  {
}
