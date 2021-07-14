package com.demo.model.dto;

import com.demo.model.CountryArea;
import com.demo.model.GeoPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/11
 */
@Value
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class CountryDTO {
    private String countryName;
    private String countryCode;
    private CountryArea countryArea;
    private GeoPosition geoPosition;
}
