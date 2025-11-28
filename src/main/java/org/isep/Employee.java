package org.isep;

import java.util.ArrayList;
import java.util.Random;

public class Employee extends Person{

    private ArrayList<Integer> EmpNbList = new ArrayList<>();
    private int NumberEmp;
    private String hiringDate;
    private String role = "Employee";

    public Employee(String name, String address, String contact, String hiringDate){
        super(name, address, contact);
        this.hiringDate = hiringDate;
        Random randomInt = new Random();
        boolean cond;
        int newNumber;
        do {
            cond = false;
            newNumber = randomInt.nextInt();
            for (int i = 0; i < EmpNbList.size(); i++) {
                if(EmpNbList.get(i) == newNumber) cond = true;
            }
        } while(cond);
        EmpNbList.add(newNumber);
        this.NumberEmp = newNumber;
    }

    public String getRole(){
        return role;
    }

}
