package com.system.eventcalendar.dto;

public class TeamDto {
    private Long id;
    private String name;
    private SportDto sportType;


    public TeamDto(Long id, String name, SportDto sportType) {
        this.id = id;
        this.name = name;
        this.sportType = sportType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SportDto getSportType() {
        return sportType;
    }

    public void setSportType(SportDto sportType) {
        this.sportType = sportType;
    }
}
