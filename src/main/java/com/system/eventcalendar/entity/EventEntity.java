package com.system.eventcalendar.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "_sport_id", nullable = false)
    private SportEntity sportType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Team_Event",
            joinColumns = @JoinColumn(name = "_event_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "_team_id",nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"_event_id", "_team_id"})
    )
    private List<TeamEntity> teams;


    public EventEntity(){teams = new ArrayList<>();}
    public EventEntity(Long id, String description, LocalDateTime dateTime, SportEntity sportType, TeamEntity team1, TeamEntity team2) {
        this.description = description;
        this.id = id;
        this.dateTime = dateTime;
        this.sportType = sportType;
        teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
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
        return teams.get(0);
    }

    public void setTeam1(TeamEntity team1) {
        teams.add(team1);
    }

    public TeamEntity getTeam2() {
        return teams.get(1);
    }

    public void setTeam2(TeamEntity team2) {
        teams.add(team2);
    }

    public void setTeams(List<TeamEntity> teams){
        this.teams=teams;
    }
    public List<TeamEntity> getTeams(){
        return this.teams;
    }
}
