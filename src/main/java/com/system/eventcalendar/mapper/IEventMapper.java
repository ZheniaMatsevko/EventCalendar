package com.system.eventcalendar.mapper;

import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface IEventMapper {
    IEventMapper INSTANCE = Mappers.getMapper(IEventMapper.class);
    EventDto entityToDto(EventEntity eventEntity);
    EventEntity dtoToEntity(EventDto eventDto);

}
