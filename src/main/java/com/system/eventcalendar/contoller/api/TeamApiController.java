package com.system.eventcalendar.contoller.api;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.TeamDto;
import com.system.eventcalendar.exception.ExceptionHelper;
import com.system.eventcalendar.service.ITeamService;
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
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamApiController {
    private final ITeamService teamService;
    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);

    @Autowired
    public TeamApiController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDto> getAll(){
        return teamService.getAllTeams();
    }

    @PostMapping
    public TeamDto addTeam(@RequestBody @Valid TeamDto teamDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = ExceptionHelper.formErrorMessage(bindingResult);
            throw new ValidationException(message);
        }
        return teamService.addNewTeam(teamDto);
    }

    @GetMapping("/{id}")
    public TeamDto findTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
    }

    @GetMapping("/bySport")
    public List<TeamDto> getTeamsBySport(@RequestParam Long id) {
        return teamService.getTeamsBySportId(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
