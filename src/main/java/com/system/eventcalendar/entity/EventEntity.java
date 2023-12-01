package com.system.eventcalendar.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne()
    @JoinColumn(name = "_sport_id", nullable = false)
    private SportEntity sportType;

    @ManyToOne()
    @JoinColumn(name = "_team1_id", nullable = false)
    private TeamEntity team1;

    @ManyToOne()
    @JoinColumn(name = "_team2_id", nullable = false)
    private TeamEntity team2;


    public EventEntity(){}
    public EventEntity(Long id, String description, LocalDateTime dateTime, SportEntity sportType, TeamEntity team1, TeamEntity team2) {
        this.description = description;
        this.id = id;
        this.dateTime = dateTime;
        this.sportType = sportType;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public SportEntity getSportType() {
        return sportType;
    }

    public void setSportType(SportEntity sportType) {
        this.sportType = sportType;
    }

    public TeamEntity getTeam1() {
        return team1;
    }

    public void setTeam1(TeamEntity team1) {
        this.team1 = team1;
    }

    public TeamEntity getTeam2() {
        return team2;
    }

    public void setTeam2(TeamEntity team2) {
        this.team2 = team2;
    }
}
