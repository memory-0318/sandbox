package com.demo;

import com.demo.mapper.PersonMapper;
import com.demo.model.Gender;
import com.demo.model.dto.PersonDTO;
import com.demo.model.vo.PersonVO;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/11
 */
public class Main {
    public static void main(String[] args) {
        PersonDTO personDTO = PersonDTO.builder()
            .setFirstName("Brian")
            .setLastName("Su")
            .setAge(20)
            .setGender(Gender.Male)
            .build();

        PersonVO personVO = PersonMapper.INSTANCE.toVO(personDTO);

        PersonDTO testPersonDTO = PersonMapper.INSTANCE.toDTO(personVO);
    }
}
