package com.system.eventcalendar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Team")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "_sport_id", nullable = false)
    private SportEntity sportType;

    public TeamEntity(){}
    public TeamEntity(String name, SportEntity sportType){
        this.name = name;
        this.sportType = sportType;
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
