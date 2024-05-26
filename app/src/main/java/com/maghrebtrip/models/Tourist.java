package com.maghrebtrip.models;

public class Tourist {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String nationality;
    private String[] preferences;

    public Tourist(Integer id, String firstName, String lastName, String email, String password, String nationality, String[] preferences) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.nationality = nationality;
        this.preferences = preferences;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNationality() {
        return nationality;
    }

    public String[] getPreferences() {
        return preferences;
    }

}
