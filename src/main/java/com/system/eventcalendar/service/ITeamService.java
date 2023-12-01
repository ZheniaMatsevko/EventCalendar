package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.TeamDto;

import java.util.List;

public interface ITeamService {
    List<TeamDto> getAllTeams();
    TeamDto getTeamById(Long id);
    List<TeamDto> getTeamsBySportId(Long id);
    TeamDto addNewTeam(TeamDto teamDto);
    void deleteTeamById(Long id);

}
