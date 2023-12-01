package com.system.eventcalendar.service;

import com.system.eventcalendar.EventCalendarApplication;
import com.system.eventcalendar.dto.SportDto;
import com.system.eventcalendar.entity.SportEntity;
import com.system.eventcalendar.mapper.ISportMapper;
import com.system.eventcalendar.repository.ISportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SportServiceImpl implements ISportService {

    static final Logger logger = LoggerFactory.getLogger(EventCalendarApplication.class);

    private final ISportRepository repository;

    @Autowired
    public SportServiceImpl(ISportRepository repository) {
        this.repository = repository;
    }
    //TODO empty sport list
    @Override
    public List<SportDto> getAllSportTypes() {
        logger.info("Getting all sports");
        return repository.findAll().stream().map(ISportMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }


    @Override
    public SportDto getSportById(long id) {
        Optional<SportEntity> sport = repository.findById(id);
        logger.info("Finding sport by id");
        if (sport.isPresent())
            return ISportMapper.INSTANCE.entityToDto(sport.get());
        else
            throw new EntityNotFoundException("Sport with id " + id + " not found");
    }

   /*
    @Override
    public SportDto getSportByName(String name) {
        return null;
    }
*/
    @Override
    public SportDto addNewSport(SportDto sportDto) {
        logger.info("Adding sport with ID: {}", sportDto.getId());
        repository.save(ISportMapper.INSTANCE.dtoToEntity(sportDto));
        logger.info("Sport added successfully.");
        return sportDto;
    }

    @Override
    public void deleteSportById(Long id) {
        Optional<SportEntity> optionalSport = repository.findById(id);

        if (optionalSport.isPresent()) {
            repository.deleteById(id);
            logger.info("Sport deleted with ID: {}", id);
        } else {
            logger.warn("Sport not found for deletion with ID: {}", id);
            throw new EntityNotFoundException("Sport not found with ID: " + id);
        }
        ISportMapper.INSTANCE.entityToDto(optionalSport.get());
    }
}
