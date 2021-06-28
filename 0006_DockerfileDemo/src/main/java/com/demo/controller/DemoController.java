package com.demo.controller;

import com.demo.model.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/6/23
 */
@RestController
public class DemoController {
    @GetMapping(value = { "hello" })
    public String sayHello() {
        try {
            return new ObjectMapper().writeValueAsString(
                ResponseWrapper.builder()
                    .setSuccess(true)
                    .setData("123")
                    .build()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Something went wrong");
        }
    }
}
