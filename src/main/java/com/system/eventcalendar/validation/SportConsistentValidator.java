package com.system.eventcalendar.validation;

import com.system.eventcalendar.dto.EventDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SportConsistentValidator implements ConstraintValidator<SportConsistent, EventDto> {
    @Override
    public boolean isValid(EventDto eventDto, ConstraintValidatorContext context) {
        if (eventDto == null || eventDto.getSportType() == null || eventDto.getTeams() == null) {
            return true;
        }
        return eventDto.getTeams().stream()
                .allMatch(teamDto -> eventDto.getSportType().getId().equals(teamDto.getSportType().getId()));
    }
}
