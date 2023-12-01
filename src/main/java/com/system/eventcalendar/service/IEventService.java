package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.EventDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventService {
    List<EventDto> getAllEvents();
    EventDto getEventById(long id);
    List<EventDto> getEventsByDate(LocalDateTime date);
    List<EventDto> getEventsBySportId(Long id);
    EventDto addNewEvent(EventDto eventDto);
    void deleteEventById(Long id);
    void updateEvent(EventDto updatedEvent);
}
