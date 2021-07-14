package com.demo.model.dto;

import com.demo.model.GeoPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/13
 */
@Value
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class CompositeDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private String belongingCountryName;
    private String belongingCountryCode;
    private GeoPosition countryPosition;
}
