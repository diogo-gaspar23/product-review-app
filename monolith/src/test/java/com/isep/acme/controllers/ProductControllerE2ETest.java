package com.isep.acme.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerE2ETest {

    @Autowired
    private MockMvc mvc;

    @Test
    void create() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/products").contentType(MediaType.APPLICATION_JSON)
                .content("{\"designation\": \"newDesignation\", \"description\": \"some description\"}");

        MvcResult result = mvc.perform(request).andReturn();

        assertEquals(result.getResponse().getStatus(), 201);
        assertTrue(result.getResponse().getContentAsString().contains("\"sku\""));
    }
}