package com.system.eventcalendar.repository;

import com.system.eventcalendar.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<EventEntity, Long> {
}
