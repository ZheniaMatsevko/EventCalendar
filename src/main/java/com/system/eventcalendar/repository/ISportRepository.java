package com.system.eventcalendar.repository;

import com.system.eventcalendar.entity.SportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISportRepository extends JpaRepository<SportEntity, Long> {
}
