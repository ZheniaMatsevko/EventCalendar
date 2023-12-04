package com.system.eventcalendar.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DifferentTeamsValidator.class})
public @interface DifferentTeams {

    String message() default "Teams must be different";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
