package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.SportDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements IEventService {
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
    public EventDto addNewEvent(EventDto eventDto) {
        return eventDto;
    }

    @Override
    public EventDto deleteEventById(Long id) {
        return null;
    }

    @Override
    public EventDto updateEventById(EventDto updatedEvent) {
        return updatedEvent;
    }
}
