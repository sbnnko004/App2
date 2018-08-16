package com.example.waterusagediary;

import java.sql.Timestamp;
import java.util.Calendar;

public class Entry {
    //private Calendar date = Calendar.getInstance();

    private String name;
    private double amount;
    private String date;

    public Entry(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public Entry(String name, double amount,String date) {
        this.name = name;
        this.amount = amount;
        this.date=date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate(){
        return  this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String toString(){
        return this.name +": "+this.amount+" litres, "+ this.date;
    }
}
