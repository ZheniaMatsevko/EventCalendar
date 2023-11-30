package com.system.eventcalendar.dto;

import java.time.LocalDateTime;

public class EventDto {
    private Long id;
    private String description;
    private LocalDateTime eventDateTime;
    private SportDto sportType;
    private TeamDto team1;
    private TeamDto team2;


    public EventDto(Long id, String description, LocalDateTime eventDateTime, SportDto sportType, TeamDto team1, TeamDto team2) {
        this.id = id;
        this.description = description;
        this.eventDateTime = eventDateTime;
        this.sportType = sportType;
        this.team1 = team1;
        this.team2 = team2;
    }

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

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public SportDto getSportType() {
        return sportType;
    }

    public void setSportType(SportDto sportType) {
        this.sportType = sportType;
    }

    public TeamDto getTeam1() {
        return team1;
    }

    public void setTeam1(TeamDto team1) {
        this.team1 = team1;
    }

    public TeamDto getTeam2() {
        return team2;
    }

    public void setTeam2(TeamDto team2) {
        this.team2 = team2;
    }
}

