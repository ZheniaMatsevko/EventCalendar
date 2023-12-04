package com.system.eventcalendar.contoller;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.exception.ExceptionHelper;
import com.system.eventcalendar.service.IEventService;
import com.system.eventcalendar.service.ISportService;
import com.system.eventcalendar.service.ITeamService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/all-events")
public class EventController {
    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);
    private final IEventService eventService;
    private final ISportService sportService;
    private final ITeamService teamService;

    @Autowired
    public EventController(IEventService eventService, ISportService sportService, ITeamService teamService) {
        this.eventService = eventService;
        this.sportService=sportService;
        this.teamService=teamService;
    }

    @GetMapping()
    public String getAll(Model model){
        List<EventDto> list = eventService.getAllEvents();
        model.addAttribute("events", list);
        model.addAttribute("sports", sportService.getAllSportTypes());
        return "events/allEvents";
    }
    @GetMapping("/{id}")
    public String findEventById(@PathVariable Long id,Model model) {
        EventDto event = eventService.getEventById(id);
        model.addAttribute("event",event);
        return "events/oneEvent";
    }

    @GetMapping("/create")
    public String showCreateEventForm(Model model) {
        model.addAttribute("event", new EventDto());
        model.addAttribute("sports", sportService.getAllSportTypes());
        model.addAttribute("teams", teamService.getAllTeams());
        return "events/addEvent";
    }
    @GetMapping("/update/{id}")
    public String showUpdateEventForm(@PathVariable Long id,Model model) {
        model.addAttribute("event", eventService.getEventById(id));
        model.addAttribute("sports", sportService.getAllSportTypes());
        model.addAttribute("teams", teamService.getAllTeams());
        return "events/updateEvent";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute("event") EventDto eventDto, @RequestParam Long sportId, @RequestParam Long team1Id, @RequestParam Long team2Id) {
        eventDto.setSportType(sportService.getSportById(sportId));
        eventDto.setTeam1(teamService.getTeamById(team1Id));
        eventDto.setTeam2(teamService.getTeamById(team2Id));
        eventDto.setId(id);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<EventDto>> violations = validator.validate(eventDto);

        if (!violations.isEmpty()) {
            String message = ExceptionHelper.formErrorMessage(violations);
            throw new ValidationException(message);
        }else{
            eventService.updateEvent(eventDto);
            return "redirect:/all-events";
        }

    }
    @GetMapping("delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return "redirect:/all-events";
    }

    @PostMapping("/create")
    public String createEvent(@ModelAttribute("event") EventDto eventDto, @RequestParam Long sportId,@RequestParam Long team1Id,@RequestParam Long team2Id) {

        eventDto.setSportType(sportService.getSportById(sportId));
        eventDto.setTeam1(teamService.getTeamById(team1Id));
        eventDto.setTeam2(teamService.getTeamById(team2Id));
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<EventDto>> violations = validator.validate(eventDto);

        if (!violations.isEmpty()) {
            String message = ExceptionHelper.formErrorMessage(violations);
            throw new ValidationException(message);
        }else{
            eventService.addNewEvent(eventDto);
            return "redirect:/all-events";
        }

    }

    @GetMapping("/bySport")
    public String getEventsBySport(@RequestParam Long id,Model model) {
        List<EventDto> list = eventService.getEventsBySportId(id);
        model.addAttribute("events", list);
        model.addAttribute("sports", sportService.getAllSportTypes());
        model.addAttribute("selectedSportId",id);
        return "events/allEvents";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
