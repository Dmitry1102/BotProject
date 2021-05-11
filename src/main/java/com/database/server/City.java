package com.database.server;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Entity
@Getter

//;

public class City {
    private Long idCity;
    private String nameCity;
    private String cityInfo;

    public City() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return idCity;
    }

}
