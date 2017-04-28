package com.example.wapp;

import java.util.HashMap;
import java.util.LinkedList;

public class Destination {

    private LinkedList<String> leads;
    private double latitude;
    private double longitude;
    private String name;

    public Destination(double latitude,double longitude,String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        leads = new  LinkedList<String>();
    }

    public String getLead(){

        return leads.poll();
    }

    public void add(String s){
        leads.add(s);
    }

    public double getLat(){
        return latitude;
    }

    public double getLong(){
        return longitude;
    }

    public String getName(){
        return name;
    }

}
