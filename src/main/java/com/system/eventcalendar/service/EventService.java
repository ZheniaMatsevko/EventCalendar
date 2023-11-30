package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.SportDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<EventDto> getAllEvents();
    EventDto getEventById(long id);
    List<EventDto> getEventsByDate(LocalDateTime date);
    List<EventDto> getEventsBySport(SportDto sport);
    void addNewEvent(EventDto eventDto);
    void deleteEventById(Long id);
    void updateEventById(Long id, EventDto updatedEvent);
}
