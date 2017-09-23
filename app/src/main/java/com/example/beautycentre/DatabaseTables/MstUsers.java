package com.example.beautycentre.DatabaseTables;

/**
 * Created by vmplapp on 11/9/17.
 */

public class MstUsers {

    int id;
    String fullname;
    String email;
    String password;
    String contactno;
    int gender;
    int active;

    public MstUsers() {
    }

    public MstUsers(int id, String fullname, String email, String password, int gender, String contactno, int active) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.contactno = contactno;
        this.active = active;
    }

    public MstUsers(String fullname, String email, String password,int gender, String contactno, int active) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.contactno = contactno;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}

