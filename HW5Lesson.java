/**
* Java.Java_Start.HW5
*
*@ Dmitriy Ostrovskiy
*@ version date Jan 27,2019
*/

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

class HW5Lesson { 

    public static Scanner scanner;
    
    public static void main(String[] args) {
        
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 50000, 42);
        persArray[1] = new Person("Petrov Petr", "manager", "petrpet@mailbox.com", "892012345", 25000, 25);
        persArray[2] = new Person("Sidorov Sidr", " middle Engineer", "sidsid@mailbox.com", "896234567", 40000, 35);
        persArray[3] = new Person("Kyznetsov Kyziy", "Designer", "kyziy@mailbox.com", "893098754", 60000, 45);
        persArray[4] = new Person("Sinicin Mark", "officer", "birdman@mailbox.com", "890567890", 20000, 20);
        
        int f, k, j;
        f = k = 0;    
        int [] ind = new int [persArray.length]; // array index
        
        int a = 40;                              // age for getAge
        for (int i = 0; i < persArray.length; i++) {
            if (a <= persArray[i].getAge()) {
                System.out.println(persArray[i]);
                f++;
                ind[f-1] = i;
            }
        }
        
        while (f != k) {                         // loop !=0
            System.out.println("Do you want to change phone number? y/ - yes/no");	
            scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            
            if (s.equals("y")) {
                j = ind[k];
                System.out.println("Input numTel  for "  + persArray[j].getName());
                String newNum = scanner.nextLine();
                persArray[j].setNumTel(newNum); 
                System.out.println(" New NumTel  : "  + persArray[j].getNumTel());
                System.out.println(persArray[j]); 
                k++;
            }
            if (f == k) break;
        }
    }
    
    public void show() {
        System.out.println("Input !"); 
    }
}

class Person {
    
    Scanner scanner;
    private String name;
    private String work;
    private String email;
    private String numTel;
    private int pay;
    private int age;    
    
    public Person(String name, String work, String email, String numTel, int pay, int age) {
        this.name = name;
        this.work = work;
        this.email = email;
        this.numTel = numTel;
        this.pay = pay;
        this.age = age;
        scanner = new Scanner(System.in);
    }
    
    public int setAge(int age) {
        this.age = age;
        return age;
    }

    public int getAge() {
        return age;
    } 
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
    
    public String getNumTel() {
        return numTel;
    }
    
    @Override
    public String toString() {
        return name + ", " +   work + ", " + email + ", " +  numTel + ", " +  pay + ", " +  age;    
    }
}