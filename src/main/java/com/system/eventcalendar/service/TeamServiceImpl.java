package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.SportDto;
import com.system.eventcalendar.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements ITeamService {
    @Override
    public List<TeamDto> getAllTeams() {
        return null;
    }

    @Override
    public TeamDto getTeamById(Long id) {
        return null;
    }

    @Override
    public List<TeamDto> getTeamsBySport(SportDto sportDto) {
        return null;
    }

    @Override
    public TeamDto addNewTeam(TeamDto teamDto) {
        return teamDto;
    }

    @Override
    public TeamDto deleteTeamById(Long id) {
        return null;
    }

}
