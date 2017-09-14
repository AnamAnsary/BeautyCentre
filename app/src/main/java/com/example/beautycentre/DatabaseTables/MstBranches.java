package com.example.beautycentre.DatabaseTables;

/**
 * Created by vmplapp on 14/9/17.
 */

public class MstBranches {

    int bid;
    String bName;
    String brAdd;
    String brContact;
    String brEmail;
    int active;

    public MstBranches() {
    }

    public MstBranches(String bName, String brAdd, String brContact, String brEmail, int active) {
        this.bName = bName;
        this.brAdd = brAdd;
        this.brContact = brContact;
        this.brEmail = brEmail;
        this.active = active;
    }

    public MstBranches(int bid, String bName, String brAdd, String brContact, String brEmail, int active) {
        this.bid = bid;
        this.bName = bName;
        this.brAdd = brAdd;
        this.brContact = brContact;
        this.brEmail = brEmail;
        this.active = active;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getBrAdd() {
        return brAdd;
    }

    public void setBrAdd(String brAdd) {
        this.brAdd = brAdd;
    }

    public String getBrContact() {
        return brContact;
    }

    public void setBrContact(String brContact) {
        this.brContact = brContact;
    }

    public String getBrEmail() {
        return brEmail;
    }

    public void setBrEmail(String brEmail) {
        this.brEmail = brEmail;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
