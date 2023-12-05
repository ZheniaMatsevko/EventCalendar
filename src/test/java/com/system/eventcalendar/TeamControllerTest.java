package com.system.eventcalendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.eventcalendar.contoller.api.TeamApiController;
import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.SportDto;
import com.system.eventcalendar.dto.TeamDto;
import com.system.eventcalendar.service.TeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TeamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamServiceImpl teamService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllTeams() throws Exception {
        List<TeamDto> mockTeams = Arrays.asList(new TeamDto(), new TeamDto());
        given(teamService.getAllTeams()).willReturn(mockTeams);

        mockMvc.perform(MockMvcRequestBuilders.get("/teams"))
                .andExpect(status().isOk());
    }


    @Test
    public void testAddTeam() throws Exception {
        TeamDto teamDto = new TeamDto(1L,"name",new SportDto(1L,"Tenis"));

        mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teamDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddTeamWithInvalidSport() throws Exception {
        TeamDto teamDto = new TeamDto(1L,"name",null);

        mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teamDto)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testDeleteTeamById() throws Exception {
        doNothing().when(teamService).deleteTeamById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/teams/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTeamById() throws Exception {
        TeamDto teamDto = new TeamDto(1L,"name",new SportDto(1L,"Tenis"));
        given(teamService.getTeamById(1L)).willReturn(teamDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/teams/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(teamDto)));
    }

    @Test
    public void testGetTeamsBySport() throws Exception {
        List<TeamDto> mockEvents = Arrays.asList(
                new TeamDto(2L,"Name1",new SportDto(1L,"Tenis")),
                new TeamDto(3L,"Name2",new SportDto(1L,"Tenis"))
        );

        given(teamService.getTeamsBySportId(1L)).willReturn(mockEvents);

        mockMvc.perform(MockMvcRequestBuilders.get("/teams/bySport").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockEvents)));
    }
}
