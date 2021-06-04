package com.demo.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/30
 */
@Data
@Builder(setterPrefix = "set", toBuilder = true)
public class Person {
    private String firstName;
    private String lastName;
    private Integer age;
}
