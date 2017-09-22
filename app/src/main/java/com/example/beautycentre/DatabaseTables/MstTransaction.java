package com.example.beautycentre.DatabaseTables;

/**
 * Created by vmplapp on 22/9/17.
 */

public class MstTransaction {
    int tid;
    int pid;
    String concernedPname;
    String ttype;
    String status;
    String transDate;
    String expDate;
    int transQuantity;
    int isparent;
    int active;

    public MstTransaction() {
    }

    public MstTransaction(int pid, String concernedPname, String ttype, String status, String transDate, String expDate, int transQuantity, int isparent, int active) {
        this.pid = pid;
        this.concernedPname = concernedPname;
        this.ttype = ttype;
        this.status = status;
        this.transDate = transDate;
        this.expDate = expDate;
        this.transQuantity = transQuantity;
        this.isparent = isparent;
        this.active = active;
    }

    public MstTransaction(int tid, int pid, String concernedPname, String ttype, String status, String transDate, String expDate, int transQuantity, int isparent, int active) {
        this.tid = tid;
        this.pid = pid;
        this.concernedPname = concernedPname;
        this.ttype = ttype;
        this.status = status;
        this.transDate = transDate;
        this.expDate = expDate;
        this.transQuantity = transQuantity;
        this.isparent = isparent;
        this.active = active;
    }

    public MstTransaction(int pid, String concernedPname, String ttype, int transQuantity, int isparent, int active) {
        this.pid = pid;
        this.concernedPname = concernedPname;
        this.ttype = ttype;
        this.transQuantity = transQuantity;
        this.isparent = isparent;
        this.active = active;
    }

    public int getTid() {

        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getConcernedPname() {
        return concernedPname;
    }

    public void setConcernedPname(String concernedPname) {
        this.concernedPname = concernedPname;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getTransQuantity() {
        return transQuantity;
    }

    public void setTransQuantity(int transQuantity) {
        this.transQuantity = transQuantity;
    }

    public int getIsparent() {
        return isparent;
    }

    public void setIsparent(int isparent) {
        this.isparent = isparent;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
