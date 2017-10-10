package com.example.beautycentre.DatabaseTables;

/**
 * Created by vmplapp on 14/9/17.
 */

public class MstBranches {

    int bid;
    int salonId;
    String bName;
    String brAdd;
    String brCPName;
    String brCPEmail;
    String brCPMob;
    int active;

    public MstBranches(int salonId, String bName, String brAdd, String brCPName, String brCPEmail, String brCPMob, int active) {
        this.salonId = salonId;
        this.bName = bName;
        this.brAdd = brAdd;
        this.brCPName = brCPName;
        this.brCPEmail = brCPEmail;
        this.brCPMob = brCPMob;
        this.active = active;
    }


    public MstBranches(int bid, int salonId, String bName, String brAdd, String brCPName, String brCPEmail, String brCPMob, int active) {

        this.bid = bid;
        this.salonId = salonId;
        this.bName = bName;
        this.brAdd = brAdd;
        this.brCPName = brCPName;
        this.brCPEmail = brCPEmail;
        this.brCPMob = brCPMob;
        this.active = active;
    }

    public MstBranches() {
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
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

    public String getBrCPName() {
        return brCPName;
    }

    public void setBrCPName(String brCPName) {
        this.brCPName = brCPName;
    }

    public String getBrCPEmail() {
        return brCPEmail;
    }

    public void setBrCPEmail(String brCPEmail) {
        this.brCPEmail = brCPEmail;
    }

    public String getBrCPMob() {
        return brCPMob;
    }

    public void setBrCPMob(String brCPMob) {
        this.brCPMob = brCPMob;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
