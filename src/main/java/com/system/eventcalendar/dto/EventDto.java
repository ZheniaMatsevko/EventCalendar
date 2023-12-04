package com.system.eventcalendar.dto;

import com.system.eventcalendar.entity.TeamEntity;
import com.system.eventcalendar.validation.DifferentTeams;
import com.system.eventcalendar.validation.SportConsistent;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SportConsistent
@DifferentTeams
public class EventDto{

    private Long id;
    @Size(max = 2000, message = "Description is longer than 2000")
    private String description;
    @NotNull(message = "Date and time must not be null")
    @Future(message = "Date and time must be in the future")
    private LocalDateTime dateTime;

    @NotNull(message = "Sport must not be null")
    private SportDto sportType;

    @Size(max = 2, message = "Number of teams must not exceed 2")
    @NotNull(message = "List of teams must not be null")
    private List<TeamDto> teams;

    public EventDto(Long id, String description, LocalDateTime dateTime, SportDto sportType, TeamDto team1, TeamDto team2) {
        this.id = id;
        this.description = description;
        this.dateTime = dateTime;

        this.sportType = sportType;
        teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
    }

    public EventDto(){teams = new ArrayList<>();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public SportDto getSportType() {
        return sportType;
    }

    public void setSportType(SportDto sportType) {
        this.sportType = sportType;
    }

    public TeamDto getTeam1() {
        return teams.get(0);
    }

    public void setTeam1(TeamDto team1) {
        teams.add(team1);
    }

    public TeamDto getTeam2() {
        return teams.get(1);
    }

    public void setTeam2(TeamDto team2) {
        teams.add(team2);
    }
    public void setTeams(List<TeamDto> teams){
        this.teams=teams;
    }
    public List<TeamDto> getTeams(){
        return this.teams;
    }
}

