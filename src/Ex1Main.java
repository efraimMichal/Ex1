import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Intro2CS, Ex1 - very basic "main template"
 * Make sure your implementation of this main performs as the Ex1Sol.jar solution implement all needed functions.
 *
 */
public class Ex1Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = "", num2="", quit = "quit";

        while (!num1.equals(quit) && !num2.equals(quit)) {
            System.out.println();
            System.out.println("Ex1 class solution:");
            System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");
            num1 = sc.next();
            if (!num1.equals("quit")) {
                System.out.println("num1= " + num1 +" is number: "+ Ex1.isNumber(num1)+" , value: "+ Ex1.number2Int(num1));
                if(Ex1.isNumber(num1)) {
                    System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
                    num2 = sc.next();
                    System.out.println("num1= " + num2 +" is number: "+ Ex1.isNumber(num2)+" , value: "+ Ex1.number2Int(num2));
                    if(Ex1.isNumber(num2)) {
                        System.out.println("Enter a base for output: (a number [2,16] ");
                        String base =sc.next();
                        if(Ex1.isDigit(base)) {
                            int intBase = Integer.parseInt(base);
                            if(intBase>=2 && intBase<=16) {
                                String sum = Ex1.sumOfNumbers(num1, num2, intBase);
                                String multi = Ex1.multiOfNumbers(num1, num2, intBase);
                                System.out.println(num1 + " + " + num2 + " = " + sum);
                                System.out.println(num1 + " * " + num2 + " = " + multi);
                                String[] arr = new String[]{num1, num2, sum, multi};
                                System.out.println("Max number over [" + num1 + "," + num2 + "," + sum + "," + multi + "] is: " + arr[Ex1.maxIndex(arr)]);
                            }else System.out.println("ERR: wrong base, should be [2,16], got ("+base+")");
                        }else System.out.println("ERR: base isn't number between [2,16]");
                    }

                }
            }
        }
    }
}