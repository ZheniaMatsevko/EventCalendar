package com.system.eventcalendar.contoller.api;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.SportDto;
import com.system.eventcalendar.exception.ExceptionHelper;
import com.system.eventcalendar.exception.NotUniqueSportNameException;
import com.system.eventcalendar.service.ISportService;
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
@RequestMapping("/sports")
public class SportApiController {
    private final ISportService sportService;
    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);

    @Autowired
    public SportApiController(ISportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping
    public List<SportDto> getAll(){
        return sportService.getAllSportTypes();
    }

    @PostMapping
    public SportDto addSport(@RequestBody @Valid SportDto sport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = ExceptionHelper.formErrorMessage(bindingResult);
            throw new ValidationException(message);
        }
        if(sportService.getAllSportTypes().contains(sport))
            throw new NotUniqueSportNameException();

        return sportService.addNewSport(sport);
    }

    @GetMapping("/{id}")
    public SportDto findSportById(@PathVariable Long id) {
        return sportService.getSportById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSport(@PathVariable Long id) {
        sportService.deleteSportById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errorMessage = "ERROR: " + e.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
