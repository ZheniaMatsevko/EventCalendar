package com.system.eventcalendar.contoller.api;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.exception.ExceptionHelper;
import com.system.eventcalendar.service.IEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventApiController {
    private final IEventService eventsService;
    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);

    @Autowired
    public EventApiController(IEventService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping
    public List<EventDto> getAll(){
        return eventsService.getAllEvents();
    }

    @PostMapping
    public EventDto addEvent(@RequestBody @Valid EventDto event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = ExceptionHelper.formErrorMessage(bindingResult);
            throw new ValidationException(message);
        }
        return eventsService.addNewEvent(event);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody @Valid EventDto event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = ExceptionHelper.formErrorMessage(bindingResult);
            throw new ValidationException(message);
        }
        event.setId(id);
        eventsService.updateEvent(event);
    }

    @GetMapping("/{id}")
    public EventDto findEventById(@PathVariable Long id) {
        return eventsService.getEventById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventsService.deleteEventById(id);
    }

    @GetMapping("/bySport")
    public List<EventDto> getEventsBySport(@RequestParam Long id) {
        return eventsService.getEventsBySportId(id);
    }

    @GetMapping("/byDate")
    public List<EventDto> getEventsByDate(@RequestParam LocalDateTime date) {
        return eventsService.getEventsByDate(date);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
