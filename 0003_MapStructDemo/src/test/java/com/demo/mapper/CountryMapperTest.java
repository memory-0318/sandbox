package com.demo.mapper;

import com.demo.model.CountryArea;
import com.demo.model.GeoPosition;
import com.demo.model.dto.CountryDTO;
import com.demo.model.vo.CountryVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/11
 */
class CountryMapperTest {

    @ParameterizedTest
    @MethodSource("provideVOArguments")
    void testToDTO(CountryVO countryVO) {
        CountryDTO countryDTO = CountryMapper.INSTANCE.toDTO(countryVO);

        assertThat(countryDTO)
            .extracting(CountryDTO::getCountryCode, CountryDTO::getCountryName, CountryDTO::getCountryArea,
                CountryDTO::getGeoPosition)
            .containsExactly(countryVO.getCode(), countryVO.getName(), countryVO.getArea(), countryVO.getPosition());
    }

    @Test
    void testToVO() {
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

    @ParameterizedTest
    @MethodSource("provideVOArguments")
    void toDTOList(CountryVO countryVO) {
        List<CountryDTO> countryDTOs = CountryMapper.INSTANCE.toDTOList(Collections.singletonList(countryVO));

        assertThat(countryDTOs)
            .hasSize(1)
            .extracting(CountryDTO::getCountryCode, CountryDTO::getCountryName, CountryDTO::getCountryArea,
                CountryDTO::getGeoPosition)
            .contains(tuple(countryVO.getCode(), countryVO.getName(), countryVO.getArea(), countryVO.getPosition()));
    }

    @ParameterizedTest
    @MethodSource("provideVOArguments")
    void toDTOSet(CountryVO countryVO) {
        Set<CountryDTO> countryDTOs = CountryMapper.INSTANCE.toDTOSet(Collections.singleton(countryVO));

        assertThat(countryDTOs)
            .hasSize(1)
            .extracting(CountryDTO::getCountryCode, CountryDTO::getCountryName, CountryDTO::getCountryArea,
                CountryDTO::getGeoPosition)
            .contains(tuple(countryVO.getCode(), countryVO.getName(), countryVO.getArea(), countryVO.getPosition()));
    }

    private static Stream<Arguments> provideVOArguments() {
        return Stream.of(
            Arguments.of(
                CountryVO.builder()
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
                    .build()
            )
        );
    }
}