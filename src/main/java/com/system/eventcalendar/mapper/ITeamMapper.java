package com.system.eventcalendar.mapper;

import com.system.eventcalendar.dto.TeamDto;
import com.system.eventcalendar.entity.TeamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ITeamMapper {
    ITeamMapper INSTANCE = Mappers.getMapper(ITeamMapper.class);
    TeamDto entityToDto(TeamEntity teamEntity);
    TeamEntity dtoToEntity(TeamDto teamDto);
}
