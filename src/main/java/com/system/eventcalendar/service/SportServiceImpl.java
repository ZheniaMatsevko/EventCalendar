package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.SportDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportServiceImpl implements SportService{
    @Override
    public List<SportDto> getAllSportTypes() {
        return null;
    }

    @Override
    public SportDto getSportById(long id) {
        return null;
    }

    @Override
    public SportDto getSportByName(String name) {
        return null;
    }

    @Override
    public void addNewSport(SportDto sportDto) {

    }

    @Override
    public SportDto deleteSportById(Long id) {
        return null;
    }
}
