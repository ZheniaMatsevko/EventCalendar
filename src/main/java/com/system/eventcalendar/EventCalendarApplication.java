package com.system.eventcalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*TODO
*  7. Look through all files - think how to improve
* */
@SpringBootApplication
@ServletComponentScan
public class EventCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventCalendarApplication.class, args);
	}

}
