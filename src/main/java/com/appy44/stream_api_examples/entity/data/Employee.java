package com.appy44.stream_api_examples.entity.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;
    private String name;
    private Address address;
    private Contact contact;
    private String designation;
    private String company;
    private double salary;

    private int age;
    private String gender;
    private List<Email> emails;
    private List<Skill> skills;
    private List<Profession> professions;
    private List<CompanyExperience> experiences;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public void setProfessions(List<Profession> professions) {
        this.professions = professions;
    }

    public List<CompanyExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<CompanyExperience> experiences) {
        this.experiences = experiences;
    }


    // Getters and setters (Lombok can be used instead)

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCompany() {
        return company;
    }

    public double getSalary() {
        return salary;
    }
}
