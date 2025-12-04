package org.isep;

import java.util.ArrayList;
import java.util.Random;

public class Employee extends Person{

    public static ArrayList<Employee> allEmployees = new ArrayList<>();
    private static ArrayList<Integer> EmpNbList = new ArrayList<>();
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
            newNumber = randomInt.nextInt(100000);
            if (newNumber < 0){
                newNumber *= -1;
            }
            for (int i = 0; i < EmpNbList.size(); i++) {
                if(EmpNbList.get(i) == newNumber) cond = true;
            }
        } while(cond);
        EmpNbList.add(newNumber);
        this.NumberEmp = newNumber;
        allEmployees.add(this);
    }

    public static Employee findEmployee(int empNumber){
        for(Employee e : allEmployees){
            if(e.getNumberEmp() == empNumber){
                return e;
            }
        }
        return null;
    }

    public static void deleteEmployee(int empNumber){
        Employee e = findEmployee(empNumber);
        if(e != null){
            allEmployees.remove(e);
            System.out.println("Employee deleted");
        }
        else {
            System.out.println("Employee not found");
        }
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void updateRole(String newRole){
        this.role = newRole;
    }

    public int getNumberEmp() {
        return NumberEmp;
    }

    public String getHiringDate() {
        return hiringDate;
    }
}
