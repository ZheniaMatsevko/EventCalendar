package com.system.eventcalendar.repository;

import com.system.eventcalendar.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamRepository extends JpaRepository<TeamEntity, Long> {
}
