package com.ms.appservice.controller;

import com.ms.appservice.model.Sample;
import com.ms.appservice.service.SampleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${app.url}" + "/sample")
public class SampleController extends BaseController<SampleService, Sample> {
}
