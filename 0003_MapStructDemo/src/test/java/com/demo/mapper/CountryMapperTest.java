package com.demo.mapper;

import com.demo.model.CountryArea;
import com.demo.model.GeoPosition;
import com.demo.model.dto.CountryDTO;
import com.demo.model.vo.CountryVO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/11
 */
class CountryMapperTest {

    @Test
    void testToVO() {
        CountryVO countryVO = CountryVO.builder()
            .setCode("TW")
            .setName("Taiwan")
            .setArea(CountryArea.builder()
                .setArea(123.0)
                .setUnit("km2")
                .build())
            .setPosition(GeoPosition.builder()
                .setLongitude(121.597366d)
                .setLatitude(25.105497d)
                .build())
            .build();

        CountryDTO countryDTO = CountryMapper.INSTANCE.toDTO(countryVO);

        assertThat(countryDTO)
            .extracting(CountryDTO::getCountryCode, CountryDTO::getCountryName, CountryDTO::getCountryArea,
                CountryDTO::getGeoPosition)
            .containsExactly(countryVO.getCode(), countryVO.getName(), countryVO.getArea(), countryVO.getPosition());
    }

    @Test
    void testToDTO() {
        CountryDTO countryDTO = CountryDTO.builder()
            .setCountryCode("TW")
            .setCountryName("Taiwan")
            .setCountryArea(CountryArea.builder()
                .setArea(123.0)
                .setUnit("km2")
                .build())
            .setGeoPosition(GeoPosition.builder()
                .setLongitude(121.597366d)
                .setLatitude(25.105497d)
                .build())
            .build();

        CountryVO countryVO = CountryMapper.INSTANCE.toVO(countryDTO);

        assertThat(countryVO)
            .extracting(CountryVO::getCode, CountryVO::getName, CountryVO::getArea,
                CountryVO::getPosition)
            .containsExactly(countryDTO.getCountryCode(), countryDTO.getCountryName(), countryDTO.getCountryArea(),
                countryDTO.getGeoPosition());
    }
}