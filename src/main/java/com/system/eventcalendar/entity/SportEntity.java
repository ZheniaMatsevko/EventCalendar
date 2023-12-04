package com.system.eventcalendar.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Sport")
public class SportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @OneToMany(mappedBy = "sportType", cascade = CascadeType.ALL)
    private List<EventEntity> events;
    @OneToMany(mappedBy = "sportType",cascade = CascadeType.ALL)
    private List<TeamEntity> teams;
    public SportEntity(){}
    public SportEntity(Long id, String name) {
        this.id=id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
