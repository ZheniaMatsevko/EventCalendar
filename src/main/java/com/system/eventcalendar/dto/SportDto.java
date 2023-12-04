package com.system.eventcalendar.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class SportDto {
    private Long id;

    @NotBlank(message = "Sport name can`t be blank")
    private String name;
    public SportDto(){}
    public SportDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportDto sport = (SportDto) o;
        return sport.getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
