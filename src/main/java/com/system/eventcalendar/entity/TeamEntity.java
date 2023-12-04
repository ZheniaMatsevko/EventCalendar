package com.system.eventcalendar.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Team")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "_sport_id", nullable = false)
    private SportEntity sportType;

    @ManyToMany(mappedBy = "teams", cascade = CascadeType.ALL)
    private List<EventEntity> events;


    public TeamEntity(){ events=new ArrayList<>();}
    public TeamEntity(Long id, String name, SportEntity sportType){
        this.id = id;
        this.name = name;
        this.sportType = sportType;
        events=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SportEntity getSportType() {
        return sportType;
    }

    public void setSportType(SportEntity sportType) {
        this.sportType = sportType;
    }
}
