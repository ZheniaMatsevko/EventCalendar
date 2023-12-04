package com.system.eventcalendar.contoller;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.SportDto;
import com.system.eventcalendar.dto.TeamDto;
import com.system.eventcalendar.exception.ExceptionHelper;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/all-teams")
public class TeamController {
    private final ITeamService teamService;
    private final ISportService sportService;
    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);

    @Autowired
    public TeamController(ITeamService teamService, ISportService sportService) {
        this.teamService=teamService;
        this.sportService=sportService;
    }

    @GetMapping()
    public String getAll(Model model){
        List<TeamDto> list = teamService.getAllTeams();
        model.addAttribute("teams", list);
        return "teams/allTeams";
    }
    //TODO delete instead of get
    @GetMapping("delete/{id}")
    public String deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return "redirect:/all-teams";
    }
    @GetMapping("/create")
    public String showCreateTeamForm(Model model) {
        model.addAttribute("team", new TeamDto());
        model.addAttribute("sports", sportService.getAllSportTypes());
        return "teams/addTeam";
    }
    @PostMapping("/create")
    public String createTeam(@ModelAttribute("team") TeamDto teamDto, @RequestParam Long sportId) {
        teamDto.setSportType(sportService.getSportById(sportId));

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<TeamDto>> violations = validator.validate(teamDto);

        if (!violations.isEmpty()) {
            String message = ExceptionHelper.formErrorMessage(violations);
            throw new ValidationException(message);
        }else {
            teamService.addNewTeam(teamDto);
            return "redirect:/all-teams";
        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
