package com.system.eventcalendar.contoller;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.SportDto;
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
import java.util.List;

@RestController
@RequestMapping("/sports")
public class SportController {
    private final ISportService sportService;
    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);

    @Autowired
    public SportController(ISportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping
    public List<SportDto> getAll(){
        return sportService.getAllSportTypes();
    }

    @PostMapping
    public SportDto addSport(@RequestBody @Valid SportDto sport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logErrors(bindingResult);
        }
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
        String errorMessage = "An unexpected error occurred: " + e.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logErrors (BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError o : allErrors){
            logger.info("error -->  " + o.getDefaultMessage());
        }
    }
}
