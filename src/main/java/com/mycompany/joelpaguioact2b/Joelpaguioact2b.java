/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.joelpaguioact2b;
import java.util.Scanner;
/**
 *
 * @author CL2-PC
 */
public class Joelpaguioact2b {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello World!");
        System.out.println("joel!");
        System.out.println("Enter a Number: ");
        int x = input.nextInt();
        System.out.println("Enter a Number: ");
        int y = input.nextInt();
        
        int sum,diff,prod,quo;
        
        sum = x + y;
        diff = x - y;
        prod = x *y;
        quo = x / y;
        
        System.out.println("Sum is: "+sum);
        System.out.println("Diff is: "+diff);
        System.out.println("Product is: "+prod);
        System.out.println("Quotient is: "+quo);
    
    }
}
