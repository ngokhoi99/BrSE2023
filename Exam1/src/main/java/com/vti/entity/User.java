package com.vti.entity;

public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private int expInYear;
    private String proSkill;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(byte expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    public enum Role {
        ADMIN, EMPLOYEE, MANAGER
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", expInYear=" + expInYear +
                ", proSkill='" + proSkill + '\'' +
                '}';
    }

    public User(int id, String fullName, String email, String proSkll) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }
}
