package com.quantitymeasurementapp;

import java.util.Scanner;

public class App {
	
    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
    	int input1 = sc.nextInt();
    	int input2 = sc.nextInt();
    	
    	demostrateFeetEquality(input1, input2);
    	demostrateInchesEquality(input1, input2);
    }
    
    public static void demostrateFeetEquality(int input1, int input2) {
    	Feet f1 = new Feet(input1);
        Feet f2 = new Feet(input2);

        System.out.println("Are equal? " + f1.equals(f2));
    }
    
    public static void demostrateInchesEquality(int input1, int input2) {
    	Inch i1 = new Inch(input1);
        Inch i2 = new Inch(input2);

        System.out.println("Are equal? " + i1.equals(i2));
    }
}