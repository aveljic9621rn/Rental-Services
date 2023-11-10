package com.example.service.userservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userId;

    private String username;
    private String password;
    private String mail;
    private String phoneNumber;
    private String dateOfBirth;
    private String name;
    private String surname;

    private String passportNumber;
    private boolean zabrana;

    private int totalRentalsInDays;

    private boolean odobren;
    public String rank_name;
    public boolean isOdobren() {return odobren;}

    public String getRank_name() {return rank_name;}

    public void setRank_name(String rank_name) {this.rank_name = rank_name;}

    public void setOdobren(boolean odobren) {this.odobren = odobren;}

    public boolean isZabrana() {
        return zabrana;
    }

    public void setZabrana(boolean zabrana) {
        this.zabrana = zabrana;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getTotalRentalsInDays() {
        return totalRentalsInDays;
    }

    public void setTotalRentalsInDays(int totalRentalsInDays) {
        this.totalRentalsInDays = totalRentalsInDays;
    }

}
