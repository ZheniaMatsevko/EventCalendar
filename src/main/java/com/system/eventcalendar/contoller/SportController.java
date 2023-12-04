package com.system.eventcalendar.contoller;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.SportDto;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/all-sports")
public class SportController {
    private final ISportService sportService;
    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);

    @Autowired
    public SportController(ISportService sportService) {
        this.sportService=sportService;
    }
    @GetMapping()
    public String getAll(Model model){
        List<SportDto> list = sportService.getAllSportTypes();
        model.addAttribute("sports", list);
        return "sport/allSports";
    }
    //TODO delete instead of get
    @GetMapping("delete/{id}")
    public String deleteSport(@PathVariable Long id) {
        sportService.deleteSportById(id);
        return "redirect:/all-sports";
    }

    @GetMapping("/create")
    public String showCreateSportForm(Model model) {
        model.addAttribute("sport", new SportDto());
        return "sport/addSport";
    }
    @PostMapping("/create")
    public String createSport(@ModelAttribute("sport") @Valid SportDto sportDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = ExceptionHelper.formErrorMessage(bindingResult);
            throw new ValidationException(message);
        }else {
            sportService.addNewSport(sportDto);
            return "redirect:/all-sports";
        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
