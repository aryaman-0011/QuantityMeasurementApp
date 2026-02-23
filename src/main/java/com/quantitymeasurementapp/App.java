package com.quantitymeasurementapp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

    	Scanner sc  = new Scanner(System.in);
    	
    	int input1 = sc.nextInt();
    	int input2 = sc.nextInt();
    	
    	Feet feet1 = new Feet(input1);
    	Feet feet2 = new Feet(input2);
    	
    	System.out.println("Equal (" + feet1.equals(feet2) + ")");
    	sc.close();
    }
}