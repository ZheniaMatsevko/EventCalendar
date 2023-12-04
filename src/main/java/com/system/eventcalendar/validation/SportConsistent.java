package com.system.eventcalendar.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SportConsistentValidator.class})
public @interface SportConsistent {
    String message() default "Sport in the event and teams must be the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
