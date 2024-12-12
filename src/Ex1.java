import java.util.Arrays;

/**
 * This class represents a simple solution for Ex1.
 * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
 * In this assignment, we will design a number formatting converter and calculator.
 * In general, we will use Strings as numbers over basis of binary till Hexa.
 * [2-16], 10-16 are represented by A,B,..G.
 * The general representation of the numbers is as a String with the following format:
 * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
 * The following are NOT in the format (not a valid number):
 * “b2”, “0b1”  , “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
 * You should implement the following static functions:
 */
public class Ex1 {
    /**
     * Convert the given number (num) to a decimal representation (as int).
     * It the given number is not in a valid format returns -1.
     * @param num a String representing a number in basis [2,16]
     * @return
     */
    public static int number2Int(String num) {
        int ans = -1;
        if(!isNumber(num)) return ans; // if it not in the correct format
        if (isDigit(num)) //if num is decimal
            return Integer.parseInt(num);

        String[] splitNumByBase = splitByB (num); // split by b so we have 2 strings, one for base and one for number
        ans = 0;
        int power = 1;
        int base = Integer.parseInt(splitNumByBase[1]);
        String number = splitNumByBase[0];
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = charToDecimal(number.charAt(i)); // convert to decimal representation, for exp '2' to 2 and 'A' to 10
            ans += digit * power;
            power = power * base; // increasing the power as the formula is digit*base^(n-i-1)
        }
        return ans;
    }

    //convert using ascii table the char to decimal representation for exp: '2' to 2 and 'A' to 10
    public static int charToDecimal(char c) {
        if (c >= '0' && c <= '9') {
            return (int) c - '0'; // using '0' as the starting base
        } else {
            return (int) c - 'A' + 10; // using 'A' as the starting base
        }
    }

    //convert using ascii table the decimal representation to string,that is value is the char representation for exp: 2 to "2" and 11 to "B"
    public static String decimalToChar(int c) {
        String decimalValue = "";
        if (c >= 10) {
            decimalValue =  String.valueOf((char)(c - 10 + (int)'A')); // using 'A' as the starting base
        } else {
            decimalValue = String.valueOf(c); // using valueOf as it return  the given int exactly in string
        }

        return decimalValue;
    }

    // the function split the given string by b so we have 2 strings, one for base and one for number
    //the base we represent as its decimal value
    public static String[] splitByB (String a) {
        String[] splitNumByBase = a.split("b");
        if(splitNumByBase.length > 1) {
            int base = 0 ;
            if(splitNumByBase[1].length() == 1) {
                base = charToDecimal(splitNumByBase[1].charAt(0));
            }
            splitNumByBase[1] = String.valueOf(base);
        }
        return splitNumByBase;

    }
    // the function check if the given string contain only digit
    // using isDigit method of Character to check if each char in the string is digit
    public static boolean isDigit (String a) {
        if(!a.isEmpty()){
            for (int i = 0; i < a.length(); i++) {
                if(!Character.isDigit(a.charAt(i))){ // using isDigit method of Character
                    return false;
                }
            }
        }
        else return false;
        return true;// if a isn't empty string and contain only digit
    }

    /**
     * This static function checks if the given String (g) is in a valid "number" format.
     * @param a a String representing a number
     * @return true iff the given String is in a number format
     */

    public static boolean isNumber(String a) {
        boolean ans = true;

        if(a==null || a.split("b").length > 2)  // if a is null or the string contain more than 1 b
            return false;

        if (isDigit(a)) { // if a is decimal representation
            return true;

        }

        String[] splitNumByBase = splitByB (a); // split by b so we have 2 strings, one for base(in decimal representation) and one for number
        if (!splitNumByBase[0].isEmpty() && splitNumByBase.length==2) { // if a include number and base
            int base = Integer.parseInt(splitNumByBase[1]); // convert the string exactly to int value
            if (base <= 16 && base>=2) {
                for (int i = 0; i < splitNumByBase[0].length(); i++) {
                    int digit = charToDecimal(splitNumByBase[0].charAt(i)); // convert  the digit to decimal representation
                    if (!(digit>=0 && digit< base)) // if the digit not in the given range
                        return false;
                }
            }
            else
                return false; // if the base not correct
        } else
            return false; // if a doesn't include both number and base for exp "b2"

        return ans; // if it reaches till here it mean the base is correct and each digit in number is in the correct range
    }
    /**
     * Calculate the number representation (in basis base)
     * of the given natural number (represented as an integer).
     * If num<0 or base is not in [2,16] the function should return "" (the empty String).
     * @param num the natural number (include 0).
     * @param base the basis [2,16]
     * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
     */
    public static String int2Number(int num, int base) {
        String ans = "";
        if (base < 2 || base > 16 || num < 0) {
            return ans;
        }
        if(num ==0){
            ans = "0";
        }
        int remainder;
        while (num > 0) {
            remainder = num % base;
            ans += decimalToChar(remainder); // add the reminder in it char representation
            num /= base;
        }
        ans = new StringBuffer(ans).reverse().toString(); // reverse the string because the formula to covert we should add the last reminder first and the first lat
        if(base != 10) {
            return ans + "b" + decimalToChar(base);
        }
        return  ans;

    }

    /**
     * Checks if the two numbers have the same value.
     * @param n1 first number
     * @param n2 second number
     * @return true iff the two numbers have the same values.
     */
    public static boolean equals(String n1, String n2) {
        boolean ans = true;
        ans = number2Int(n1) == number2Int(n2); // covert the 2 number to decimal so we can easily compare
        return ans;
    }

    /**
     * This static function search for the array index with the largest number (in value).
     * In case there are more than one maximum - returns the first index.
     * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
     * @param arr an array of numbers
     * @return the index in the array in with the largest number (in value).
     *
     */
    public static int maxIndex(String[] arr) {
        int ans = 0;
        if ( arr == null || arr.length == 0 )
            return -1; // null or empty
       int max = number2Int(arr[ans]);
        for ( int i = 1; i < arr.length; i++ )
        {
            if ( number2Int(arr[i]) > max) { // comparing the current value to the max
                ans = i;
                max =  number2Int(arr[i]); // using parameter max so we won't call the function each time we compare when the max didn't change
            }
        }
        return ans; // position of the first largest found
    }

    // the function get 2 string, each can be in different format, convert to decimal and calculate multi/sum and return the value in the new base
    public static String calculateNumbers(String num1, String num2, int base, char operator) {
        int intNum1 = number2Int(num1) , intNum2 = number2Int(num2); //convert each num to decimal
        String ans = "";
        if(operator == '+') {
            ans = int2Number(intNum1 + intNum2, base);
        }
        else if(operator == '*') {
            ans = int2Number(intNum1*intNum2, base);
        }
        return ans;

    }

}