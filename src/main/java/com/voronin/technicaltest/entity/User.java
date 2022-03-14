package com.voronin.technicaltest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voronin.technicaltest.validation.UserGender;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    @NotBlank(message = "Name is mandatory")
    private String userName;

    @NotNull(message = "birthDate is mandatory")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?\\d{10}$", message = "Phone number format is incorrect")
    @Column(name = "phone_number")
    private String phoneNumber;

    @UserGender(message = "Gender is incorrect")
    private String gender;

    //constructors

    public User() {
    }

    public User(String userName, LocalDate birthDate, String country) {
        this.userName = userName;
        this.birthDate = birthDate;
        this.country = country;
    }

    public User(String userName, LocalDate birthDate, String country, String phoneNumber, String gender) {
        this.userName = userName;
        this.birthDate = birthDate;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    //Getters and setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
