package com.example.beautycentre.DatabaseTables;

/**
 * Created by vmplapp on 12/9/17.
 */

public class MstProducts  {
    int pid;
    String pname;
    String descrip;
    String pbrand;
    String pcategory;
    int quantity;
    int stockAlert;
    int active;

    public MstProducts(int pid, String pname) {
        this.pid = pid;
        this.pname = pname;
    }

    public MstProducts(int pid, String pname, String descrip, String pbrand, String pcategory, int quantity, int stockAlert, int active) {

        this.pid = pid;
        this.pname = pname;
        this.descrip = descrip;

        this.pbrand = pbrand;
        this.pcategory = pcategory;
        this.quantity = quantity;
        this.stockAlert = stockAlert;
        this.active = active;
    }

    public MstProducts(String pname, String descrip, String pbrand, String pcategory, int quantity, int stockAlert, int active) {

        this.pname = pname;
        this.descrip = descrip;
        this.pbrand = pbrand;
        this.pcategory = pcategory;
        this.quantity = quantity;
        this.stockAlert = stockAlert;
        this.active = active;
    }

    public MstProducts() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPbrand() {
        return pbrand;
    }

    public void setPbrand(String pbrand) {
        this.pbrand = pbrand;
    }

    public String getPcategory() {
        return pcategory;
    }

    public void setPcategory(String pcategory) {
        this.pcategory = pcategory;
    }

    public int getStockAlert() {
        return stockAlert;
    }

    public void setStockAlert(int stockAlert) {
        this.stockAlert = stockAlert;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}