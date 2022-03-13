package com.voronin.technicaltest.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    @NotBlank(message = "User name is incorrect")
    private String userName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotBlank
    @Pattern(regexp = "^France$", message = "Service unavailable in this country")
    private String country;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?\\d{10}$", message = "Phone number is incorrect")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Pattern(regexp = "^MALE$|^FEMALE", message = "Available options MALE, FEMALE")
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
