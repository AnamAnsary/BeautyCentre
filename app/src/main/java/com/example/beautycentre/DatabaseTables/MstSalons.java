package com.example.beautycentre.DatabaseTables;

/**
 * Created by vmplapp on 14/9/17.
 */

public class MstSalons {

    int sid;
    String sname;
    String descrip;
    String owner_name;
    int active;

    public MstSalons(String sname, String descrip, String owner_name, int active) {
        this.sname = sname;
        this.descrip = descrip;
        this.owner_name = owner_name;
        this.active = active;
    }

    public MstSalons(int sid, String sname, String descrip, String owner_name, int active) {
        this.sid = sid;
        this.sname = sname;
        this.descrip = descrip;
        this.owner_name = owner_name;
        this.active = active;
    }

    public MstSalons() {

    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}


