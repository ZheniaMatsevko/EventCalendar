package com.system.eventcalendar.validation;

import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.TeamDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DifferentTeamsValidator implements ConstraintValidator<DifferentTeams, EventDto> {

    @Override
    public boolean isValid(EventDto eventDto, ConstraintValidatorContext context) {
        if (eventDto == null || eventDto.getTeams() == null || eventDto.getTeams().size() != 2) {
            return true;
        }

        TeamDto team1 = eventDto.getTeams().get(0);
        TeamDto team2 = eventDto.getTeams().get(1);

        return !team1.getId().equals(team2.getId());
    }
}
