package com.demo.mapper;

import com.demo.model.CountryArea;
import com.demo.model.Gender;
import com.demo.model.GeoPosition;
import com.demo.model.dto.CompositeDTO;
import com.demo.model.vo.CountryVO;
import com.demo.model.vo.PersonVO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/13
 */
class CompositeMapperTest {

    @Test
    void testToDTO() {
        PersonVO personVO = PersonVO.builder()
            .setFirstName("Brian")
            .setLastName("Su")
            .setAge(30)
            .setGender(Gender.Male)
            .build();

        CountryVO countryVO = CountryVO.builder()
            .setName("Taiwan")
            .setCode("TW")
            .setArea(CountryArea.builder()
                .setArea(123.0)
                .setUnit("km2")
                .build())
            .setPosition(GeoPosition.builder()
                .setLongitude(121.597366d)
                .setLatitude(25.105497d)
                .build())
            .build();

        CompositeDTO compositeDTO = CompositeMapper.INSTANCE.toDTO(personVO, countryVO);
        assertThat(compositeDTO)
            .extracting(CompositeDTO::getFirstName, CompositeDTO::getLastName, CompositeDTO::getAge,
                CompositeDTO::getBelongingCountryName, CompositeDTO::getBelongingCountryCode,
                CompositeDTO::getCountryPosition)
            .containsExactly(personVO.getFirstName(), personVO.getLastName(), personVO.getAge(), countryVO.getName(),
                countryVO.getCode(), countryVO.getPosition());
    }
}