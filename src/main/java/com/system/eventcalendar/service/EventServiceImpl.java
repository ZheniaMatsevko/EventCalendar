package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.SportDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Override
    public List<EventDto> getAllEvents() {
        return null;
    }

    @Override
    public EventDto getEventById(long id) {
        return null;
    }

    @Override
    public List<EventDto> getEventsByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<EventDto> getEventsBySport(SportDto sport) {
        return null;
    }

    @Override
    public void addNewEvent(EventDto eventDto) {

    }

    @Override
    public void deleteEventById(Long id) {

    }

    @Override
    public void updateEventById(Long id, EventDto updatedEvent) {

    }
}
