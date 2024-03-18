package com.example.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("First name")
    private String firstName;

    @JsonProperty("Member since")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate memberSince;
    @JsonProperty("Member till")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate memberTill;

    @JsonProperty("Gender")
    private String gender;

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public LocalDate getMemberTill() {
        return memberTill;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public void setMemberTill(LocalDate memberTill) {
        this.memberTill = memberTill;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", memberSince='" + memberSince + '\'' +
                ", memberTill='" + memberTill + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
