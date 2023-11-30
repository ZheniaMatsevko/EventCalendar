package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.SportDto;
import com.system.eventcalendar.dto.TeamDto;

import java.util.List;

public interface TeamService {
    List<TeamDto> getAllTeams();
    TeamDto getTeamById(Long id);
    List<TeamDto> getTeamsBySport(SportDto sportDto);
    void addNewTeam(TeamDto teamDto);
    TeamDto deleteTeamById(Long id);

}
