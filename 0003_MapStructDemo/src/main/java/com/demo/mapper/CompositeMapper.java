package com.demo.mapper;

import com.demo.model.dto.CompositeDTO;
import com.demo.model.vo.CountryVO;
import com.demo.model.vo.PersonVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/5/13
 */
@Mapper
public interface CompositeMapper {
    CompositeMapper INSTANCE = Mappers.getMapper(CompositeMapper.class);

    @Mappings({
        @Mapping(source = "countryVO.name", target = "belongingCountryName"),
        @Mapping(source = "countryVO.code", target = "belongingCountryCode"),
        @Mapping(source = "countryVO.position", target = "countryPosition")
    })
    CompositeDTO toDTO(PersonVO personVO, CountryVO countryVO);
}
