package com.demo.model;

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
public class CountryArea {
    private Double area;
    private String unit;
}
