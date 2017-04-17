package com.example.wapp;

import java.util.HashMap;
import java.util.LinkedList;

public class Destination {

    private LinkedList<String> leads;
    private int latitude;
    private int longitude;
    private String name;

    public Destination(int latitude,int longitude,String name) {
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

    public int getLat(){
        return latitude;
    }

    public int getLong(){
        return longitude;
    }

    public String getName(){
        return name;
    }

}
