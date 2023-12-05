package com.system.eventcalendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.eventcalendar.contoller.api.EventApiController;
import com.system.eventcalendar.contoller.api.SportApiController;
import com.system.eventcalendar.dto.EventDto;
import com.system.eventcalendar.dto.SportDto;
import com.system.eventcalendar.dto.TeamDto;
import com.system.eventcalendar.service.SportServiceImpl;
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

@WebMvcTest(SportApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class SportControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SportServiceImpl sportService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllSports() throws Exception {
        List<SportDto> mockSports = Arrays.asList(new SportDto(), new SportDto());
        given(sportService.getAllSportTypes()).willReturn(mockSports);

        mockMvc.perform(MockMvcRequestBuilders.get("/sports"))
                .andExpect(status().isOk());
    }


    @Test
    public void testAddSport() throws Exception {
        SportDto sportDto = new SportDto(1L,"Tenis");

        mockMvc.perform(MockMvcRequestBuilders.post("/sports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sportDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddSportWithInvalidName() throws Exception {
        SportDto sportDto = new SportDto(1L,"Tenis");
        List<SportDto> all = new ArrayList<>();
        all.add(sportDto);
        given(sportService.getAllSportTypes()).willReturn(all);
        mockMvc.perform(MockMvcRequestBuilders.post("/sports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sportDto)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testDeleteSportById() throws Exception {
        doNothing().when(sportService).deleteSportById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/sports/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetSportById() throws Exception {
        SportDto sportDto = new SportDto(1L,"Tenis");
        given(sportService.getSportById(1L)).willReturn(sportDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/sports/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(sportDto)));
    }

}
