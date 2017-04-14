package com.example.wapp;

import java.util.HashMap;
import java.util.LinkedList;

public class Destination {

    private LinkedList<String> leads;

    public Destination() {
        leads = new  LinkedList<String>();
    }

    public String getLead(){

        return leads.poll();
    }

    public void add(String s){
        leads.add(s);
    }
}
