package org.isep;

import java.util.ArrayList;
import java.util.Random;

public class Person {
    private static ArrayList<Integer> IDList = new ArrayList<>();
    private int ID;
    private String name;
    private String address;
    private String contact;

    public Person(String name, String address, String contact){
        this.name = name;
        this.address = address;
        this.contact = contact;
        Random randomInt = new Random();
        boolean cond;
        int newID;
        do {
            cond = false;
            newID = randomInt.nextInt();
            for (int i = 0; i < IDList.size(); i++) {
                if(IDList.get(i) == newID) cond = true;
            }
        } while(cond);
        IDList.add(newID);
        this.ID = newID;
    }

    public void getInfos(){
        System.out.println("ID : " + ID + "\nname : " + name + "\naddress: " + address + "\n contact : " + contact);
    }

    public int getID(){
        return ID;
    }

}
