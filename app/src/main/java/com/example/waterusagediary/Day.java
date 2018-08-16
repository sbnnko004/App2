package com.example.waterusagediary;

public class Day {
    private String day;
    private double total;

    public Day(String day) {
        this.day = day;
        total = 0;
    }

    public void addToTotal(double amount){
        total+=amount;
    }
    public String getDay() {
        return day;
    }

    public double getTotal() {
        return total;
    }
}
