package com.system.eventcalendar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TeamDto {
    private Long id;
    @NotBlank(message = "Sport name can`t be blank")
    private String name;
    @NotNull(message = "Sport must not be null")
    private SportDto sportType;
    public TeamDto(){}
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
