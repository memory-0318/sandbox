package com.demo.mapper;

import com.demo.model.Gender;
import com.demo.model.dto.PersonDTO;
import com.demo.model.vo.PersonVO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/11
 */
class PersonMapperTest {

    @Test
    void testToDTO() {
        PersonVO personVO = PersonVO.builder()
            .setFirstName("Brian")
            .setLastName("Su")
            .setAge(30)
            .setGender(Gender.Male)
            .build();
        PersonDTO personDTO = PersonMapper.INSTANCE.toDTO(personVO);

        assertThat(personDTO)
            .extracting(PersonDTO::getFirstName, PersonDTO::getLastName, PersonDTO::getAge, PersonDTO::getGender)
            .containsExactly(personVO.getFirstName(), personVO.getLastName(), personVO.getAge(), personVO.getGender());
    }

    @Test
    void testToVO() {
        PersonDTO personDTO = PersonDTO.builder()
            .setFirstName("Brian")
            .setLastName("Su")
            .setAge(30)
            .setGender(Gender.Male)
            .build();
        PersonVO personVO = PersonMapper.INSTANCE.toVO(personDTO);

        assertThat(personVO)
            .extracting(PersonVO::getFirstName, PersonVO::getLastName, PersonVO::getAge, PersonVO::getGender)
            .containsExactly(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getAge(),
                personDTO.getGender());
    }
}