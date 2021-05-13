package com.demo.model.dto;

import com.demo.model.GeoPosition;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/13
 */
@Data
@Setter(value = AccessLevel.NONE)
@Builder(setterPrefix = "set", toBuilder = true)
public class CompositeDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private String belongingCountryName;
    private String belongingCountryCode;
    private GeoPosition countryPosition;
}
