package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.SportDto;

import java.util.List;

public interface ISportService {
    List<SportDto> getAllSportTypes();
    SportDto getSportById(long id);
    SportDto getSportByName(String name);
    SportDto addNewSport(SportDto sportDto);
    SportDto deleteSportById(Long id);
}
