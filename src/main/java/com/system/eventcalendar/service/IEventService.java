package com.system.eventcalendar.service;

import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.SportDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventService {
    List<EventDto> getAllEvents();
    EventDto getEventById(long id);
    List<EventDto> getEventsByDate(LocalDateTime date);
    List<EventDto> getEventsBySport(SportDto sport);
    EventDto addNewEvent(EventDto eventDto);
    EventDto deleteEventById(Long id);
    EventDto updateEventById(EventDto updatedEvent);
}
