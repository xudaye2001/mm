package com.bookindle.boosystem.entity.weather;

import com.bookindle.boosystem.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
@Setter
@Getter
@Entity
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String city;

    @JsonBackReference
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name="city_user",joinColumns={@JoinColumn(name="c_id")},inverseJoinColumns={@JoinColumn(name="u_id")})
    private Set<User> userList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private List<Weather> weatherList;


    public City(String city) {
        this.city = city;
    }
    public City(){}


}
