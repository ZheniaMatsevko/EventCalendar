package com.system.eventcalendar.service;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.TeamDto;
import com.system.eventcalendar.entity.TeamEntity;
import com.system.eventcalendar.mapper.ITeamMapper;
import com.system.eventcalendar.repository.ITeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements ITeamService {
    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);

    private final ITeamRepository repository;

    @Autowired
    public TeamServiceImpl(ITeamRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<TeamDto> getAllTeams() {
        logger.info("Getting all teams");
        return repository.findAll().stream().map(ITeamMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public TeamDto getTeamById(Long id) {
        Optional<TeamEntity> team = repository.findById(id);
        logger.info("Finding team by id");
        if (team.isPresent())
            return ITeamMapper.INSTANCE.entityToDto(team.get());
        else
            throw new EntityNotFoundException("Team with id " + id + " not found");
    }

    @Override
    public List<TeamDto> getTeamsBySportId(Long id) {
        List<TeamEntity> events = repository.findBySportTypeId(id);

        logger.info("Retrieved {} teams for sport with id" + id, events.size());

        return events.stream()
                .map(ITeamMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto addNewTeam(TeamDto teamDto) {
        logger.info("Adding team with ID: {}", teamDto.getId());
        repository.save(ITeamMapper.INSTANCE.dtoToEntity(teamDto));
        logger.info("Team added successfully.");
        return teamDto;
    }

    @Override
    public void deleteTeamById(Long id) {
        Optional<TeamEntity> optionalTeam = repository.findById(id);

        if (optionalTeam.isPresent()) {
            repository.deleteById(id);
            logger.info("Team deleted with ID: {}", id);
        } else {
            logger.warn("Team not found for deletion with ID: {}", id);
            throw new EntityNotFoundException("Team not found with ID: " + id);
        }
        ITeamMapper.INSTANCE.entityToDto(optionalTeam.get());
    }

}
