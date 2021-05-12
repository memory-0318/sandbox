package com.demo.mapper;

import com.demo.model.dto.PersonDTO;
import com.demo.model.vo.PersonVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/11
 */
@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toDTO(PersonVO personVO);

    PersonVO toVO(PersonDTO personDTO);
}
