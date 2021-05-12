package com.demo.model.vo;

import com.demo.model.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/11
 */
@Data
@Setter(value = AccessLevel.NONE)
@Builder(setterPrefix = "set", toBuilder = true)
public class PersonVO {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
}
