package com.example.beautycentre.DatabaseTables;

/**
 * Created by vmplapp on 12/9/17.
 */

public class MstProducts  {
    int pid;
    String pname;
    String descrip;
    int intial_quantity;
    int quantity;
    int active;

    public MstProducts() {
    }

    public MstProducts(String pname, String descrip, int intial_quantity, int quantity, int active) {
        this.pname = pname;
        this.descrip = descrip;
        this.intial_quantity = intial_quantity;
        this.quantity = quantity;
        this.active = active;
    }

    public MstProducts(int pid, String pname, String descrip, int intial_quantity, int quantity, int active) {
        this.pid = pid;
        this.pname = pname;
        this.descrip = descrip;
        this.intial_quantity = intial_quantity;
        this.quantity = quantity;
        this.active = active;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getIntial_quantity() {
        return intial_quantity;
    }

    public void setIntial_quantity(int intial_quantity) {
        this.intial_quantity = intial_quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}