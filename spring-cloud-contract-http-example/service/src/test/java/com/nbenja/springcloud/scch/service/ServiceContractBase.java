package com.nbenja.springcloud.scch.service;


import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ServiceContractBase {

    @Autowired
    private ServiceController serviceController;

    @Before
    public void before() {
        RestAssuredMockMvc.standaloneSetup(serviceController);
    }
}
