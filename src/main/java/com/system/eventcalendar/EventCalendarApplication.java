package com.system.eventcalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@ServletComponentScan
public class EventCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventCalendarApplication.class, args);
	}

}
