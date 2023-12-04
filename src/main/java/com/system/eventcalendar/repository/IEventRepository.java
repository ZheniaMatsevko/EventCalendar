package com.system.eventcalendar.repository;

import com.system.eventcalendar.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findByDateTimeBetween(LocalDateTime dateTime, LocalDateTime dateTime2);

    List<EventEntity> findBySportTypeIdOrderByDateTimeAsc(Long sportId);
}
