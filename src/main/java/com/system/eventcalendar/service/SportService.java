package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.SportDto;

import java.time.LocalDateTime;
import java.util.List;

public interface SportService {
    List<SportDto> getAllSportTypes();
    SportDto getSportById(long id);
    SportDto getSportByName(String name);
    void addNewSport(SportDto sportDto);
    SportDto deleteSportById(Long id);
}
