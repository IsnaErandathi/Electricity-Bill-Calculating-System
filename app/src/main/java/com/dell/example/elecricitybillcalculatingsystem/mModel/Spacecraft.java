package com.dell.example.elecricitybillcalculatingsystem.mModel;

public class Spacecraft {


    private int b_id;
    private String invoice_no;
    private String units;
    private String balance;

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    /*@Override
    public String toString() {
        return c_name;
    }*/
}
