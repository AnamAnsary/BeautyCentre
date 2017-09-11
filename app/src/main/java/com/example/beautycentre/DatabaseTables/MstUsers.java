package com.example.beautycentre.DatabaseTables;

/**
 * Created by vmplapp on 11/9/17.
 */

public class MstUsers {

    int id;
    String fullname;
    String email;
    String password;
    String username;
    String contactno;
    String usertype;
    int active;

    public MstUsers() {
    }

    public MstUsers(int id, String fullname, String email, String password, String username, String contactno, String usertype, int active) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.contactno = contactno;
        this.usertype = usertype;
        this.active = active;
    }

    public MstUsers(String fullname, String email, String password, String username, String contactno, String usertype, int active) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.contactno = contactno;
        this.usertype = usertype;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}

