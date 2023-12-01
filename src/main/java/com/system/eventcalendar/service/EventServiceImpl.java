package com.system.eventcalendar.service;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.entity.EventEntity;
import com.system.eventcalendar.mapper.IEventMapper;
import com.system.eventcalendar.repository.IEventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {

    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);

    private final IEventRepository repository;

    @Autowired
    public EventServiceImpl(IEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventDto> getAllEvents() {
        logger.info("Getting all events");
        return repository.findAll().stream().map(IEventMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public EventDto getEventById(long id) {
        Optional<EventEntity> event = repository.findById(id);
        logger.info("Finding event by id");
        if (event.isPresent())
            return IEventMapper.INSTANCE.entityToDto(event.get());
        else
            throw new EntityNotFoundException("Event with id " + id + " not found");
    }

    @Override
    public List<EventDto> getEventsByDate(LocalDateTime date) {
        LocalDateTime startDateTime = date.toLocalDate().atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusDays(1);



        List<EventEntity> events = repository.findByDateTimeBetween(startDateTime, endDateTime);

        logger.info("Retrieved {} events on " + date, events.size());

        return events.stream()
                .map(IEventMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<EventDto> getEventsBySportId(Long id) {
        List<EventEntity> events = repository.findBySportTypeId(id);

        logger.info("Retrieved {} events for sport with id" + id, events.size());

        return events.stream()
                .map(IEventMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto addNewEvent(EventDto eventDto) {
        logger.info("Adding event with ID: {}", eventDto.getId());
        repository.save(IEventMapper.INSTANCE.dtoToEntity(eventDto));
        logger.info("Event added successfully.");
        return eventDto;
    }

    @Override
    public void deleteEventById(Long id) {
        Optional<EventEntity> optionalEvent = repository.findById(id);

        if (optionalEvent.isPresent()) {
            repository.deleteById(id);
            logger.info("Event deleted with ID: {}", id);
        } else {
            logger.warn("Event not found for deletion with ID: {}", id);
            throw new EntityNotFoundException("Event not found with ID: " + id);
        }
        IEventMapper.INSTANCE.entityToDto(optionalEvent.get());
    }

    @Override
    public void updateEvent(EventDto updatedEvent) {
        Optional<EventEntity> optionalEvent = repository.findById(updatedEvent.getId());

        if (optionalEvent.isPresent()) {
            EventEntity existingEvent = optionalEvent.get();
            BeanUtils.copyProperties(updatedEvent, existingEvent, "id"); // Exclude copying the 'id'
            repository.save(existingEvent);

            logger.info("Event updated with ID: {}", updatedEvent.getId());
        } else {
            throw new EntityNotFoundException("Event not found with ID: " + updatedEvent.getId());
        }
    }
}
