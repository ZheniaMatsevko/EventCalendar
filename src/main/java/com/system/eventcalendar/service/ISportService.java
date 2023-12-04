package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.SportDto;

import java.util.List;

public interface ISportService {
    List<SportDto> getAllSportTypes();
    SportDto getSportById(long id);
    SportDto addNewSport(SportDto sportDto);
    void deleteSportById(Long id);
}
