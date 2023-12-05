package com.system.eventcalendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.eventcalendar.contoller.api.EventApiController;
import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.SportDto;
import com.system.eventcalendar.dto.TeamDto;
import com.system.eventcalendar.service.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventServiceImpl eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllEvents() throws Exception {
        List<EventDto> mockEvents = Arrays.asList(new EventDto(), new EventDto());
        given(eventService.getAllEvents()).willReturn(mockEvents);

        mockMvc.perform(MockMvcRequestBuilders.get("/events"))
                .andExpect(status().isOk());
    }


    @Test
    public void testAddEvent() throws Exception {
        EventDto eventDto = new EventDto(99L,"Desc", LocalDateTime.parse("2023-12-19T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),new TeamDto(3L,"Name2",new SportDto(1L,"Tenis")));

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                     .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddEventWithInvalidDate() throws Exception {
        EventDto eventDto = new EventDto(99L,"Desc", LocalDateTime.parse("2022-12-19T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),new TeamDto(3L,"Name2",new SportDto(1L,"Tenis")));

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testAddEventWithInvalidSport() throws Exception {
        EventDto eventDto = new EventDto(99L,"Desc", LocalDateTime.parse("2023-12-19T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(2L,"Hockey")),new TeamDto(3L,"Name2",new SportDto(1L,"Tenis")));

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testUpdateEvent() throws Exception {
        EventDto eventDto = new EventDto(99L,"Desc", LocalDateTime.parse("2023-12-19T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),new TeamDto(3L,"Name2",new SportDto(1L,"Tenis")));
        doNothing().when(eventService).updateEvent(eventDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/events/{eventId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateEventWithInvalidTeams() throws Exception {
        EventDto eventDto = new EventDto(99L,"Desc", LocalDateTime.parse("2023-12-19T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")));
        doNothing().when(eventService).updateEvent(eventDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/events/{eventId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    public void testDeleteEventById() throws Exception {
        long eventIdToDelete = 1L;
        doNothing().when(eventService).deleteEventById(eventIdToDelete);

        mockMvc.perform(MockMvcRequestBuilders.delete("/events/{id}", eventIdToDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEventById() throws Exception {
        EventDto eventDto = new EventDto(99L,"Desc", LocalDateTime.parse("2023-12-19T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),new TeamDto(3L,"Name2",new SportDto(1L,"Tenis")));

        given(eventService.getEventById(99L)).willReturn(eventDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/events/99"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(eventDto)));
    }

    @Test
    public void testGetEventsBySport() throws Exception {
        List<EventDto> mockEvents = Arrays.asList(
                new EventDto(99L,"Desc", LocalDateTime.parse("2023-12-19T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),new TeamDto(3L,"Name2",new SportDto(1L,"Tenis"))),
                new EventDto(1L,"Desc2", LocalDateTime.parse("2023-12-22T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),new TeamDto(3L,"Name2",new SportDto(1L,"Tenis")))
        );

        given(eventService.getEventsBySportId(1L)).willReturn(mockEvents);

        mockMvc.perform(MockMvcRequestBuilders.get("/events/bySport").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockEvents)));
    }

    @Test
    public void testGetEventsByDate() throws Exception {
        List<EventDto> mockEvents = Arrays.asList(
                new EventDto(99L,"Desc", LocalDateTime.parse("2023-12-19T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),new TeamDto(3L,"Name2",new SportDto(1L,"Tenis"))),
                new EventDto(1L,"Desc2", LocalDateTime.parse("2023-12-19T12:34"),new SportDto(1L,"Tenis"),new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),new TeamDto(3L,"Name2",new SportDto(1L,"Tenis")))
        );

        given(eventService.getEventsByDate(LocalDateTime.parse("2023-12-19T12:34"))).willReturn(mockEvents);

        mockMvc.perform(MockMvcRequestBuilders.get("/events/byDate").param("date", "2023-12-19T12:34"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockEvents)));
    }
}
