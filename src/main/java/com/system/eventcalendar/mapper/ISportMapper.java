package com.system.eventcalendar.mapper;

import com.system.eventcalendar.dto.SportDto;
import com.system.eventcalendar.entity.SportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ISportMapper {
    ISportMapper INSTANCE = Mappers.getMapper(ISportMapper.class);
    SportDto entityToDto(SportEntity sportEntity);
    SportEntity dtoToEntity(SportDto sportDto);
}
