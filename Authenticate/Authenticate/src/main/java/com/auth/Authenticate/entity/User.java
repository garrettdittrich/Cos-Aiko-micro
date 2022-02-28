package com.auth.Authenticate.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "f_name")
    private String fName;

    @Column(name = "l_name")
    private String lName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    //////// CONSTRUCTORS \\\\\\\\
    public User() {

    }

    public User(Integer id, String fName, String lName, String email, String password) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    //////// GETTERS \\\\\\\\
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //////// GETTERS \\\\\\\\
    public void setId(Integer id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}