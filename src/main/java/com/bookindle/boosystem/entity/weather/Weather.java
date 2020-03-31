package com.bookindle.boosystem.entity.weather;

import com.bookindle.boosystem.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
public class Weather extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Temporal(TemporalType.DATE)
    private Date date;

    private String week;

    private String nightWeather;

    private String nightTemplow;

    private String nightWindpower;

    private String dayWeather;

    private String dayTempow;

    private String dayWindpower;

    @ManyToOne
    private City city;


    public Weather() { }
}
