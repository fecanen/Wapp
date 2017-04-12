package com.example.wapp;


import java.util.HashMap;

public class Destination {
    private int id;
    private String city;
    private HashMap<Integer, String>  leads;


    public Destination(int id, String city, HashMap leads){
        this.id = id;
        this.city = city;
        leads = new HashMap<Integer, String>();
    }

    public String getCity(){

        return city;
    }

    public int getId(){

        return id;
    }

    public String getLead(int lead){

        return leads.get(lead);
    }

}
