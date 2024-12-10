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
        if(!isNumber(num)) return ans;
        if (isDigit(num))
            return Integer.parseInt(num);

        String[] splitNumByBase = splitByB (num);
        ans = 0;
        int power = 1;
        int base = Integer.parseInt(splitNumByBase[1]);
        String number = splitNumByBase[0];
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = charToDecimal(number.charAt(i));
            if (digit < 0 || digit >= base) {
                return -1;
            }
            ans += digit * power;
            power = power * base;
        }
        return ans;
    }
    public static int charToDecimal(char c) {
        if (c >= '0' && c <= '9') {
            return (int) c - '0';
        } else {
            return (int) c - 'A' + 10;
        }
    }
    public static String decimalToChar(int c) {
        String base = "";
        if (c >= 2 && c <= 16) {
            if (c == 10) {
                base = "A";
            } else if (c == 11) {
                base = "B";
            } else if (c == 12) {
                base = "C";
            } else if (c == 13) {
                base = "D";
            } else if (c == 14) {
                base = "E";
            } else if (c == 15) {
                base = "F";
            } else if (c == 16) {
                base = "G";
            } else {
                base = String.valueOf(c);
            }
        }
        return base;
    }

    /**
     * This static function checks if the given String (g) is in a valid "number" format.
     * @param a a String representing a number
     * @return true iff the given String is in a number format
     */

    public static String[] splitByB (String a) {
        String[] splitNumByBase = a.split("b");
        if(splitNumByBase.length > 1) {
            int base = charToDecimal(splitNumByBase[1].charAt(0));
            splitNumByBase[1] = String.valueOf(base);
        }
        return splitNumByBase;

    }
    public static boolean isDigit (String a) {
        if(!a.isEmpty()){
            for (int i = 0; i < a.length(); i++) {
                if(!Character.isDigit(a.charAt(i))){
                    return false;
                }
            }
        }
        else return false;
        return true;
    }
    public static boolean isNumber(String a) {
        boolean ans = true;

        if(a==null || a.split("b").length > 2) return false;

        if (isDigit(a)) {
            return true;

        }

        String[] splitNumByBase = splitByB (a);
        if (!splitNumByBase[0].isEmpty() && splitNumByBase.length==2) {
            int base = Integer.parseInt(splitNumByBase[1]);
            if (base <= 16 && base>=2) {
                for (int i = 0; i < splitNumByBase[0].length(); i++) {
                    if (!((splitNumByBase[0].charAt(i) >= '0' &&
                            splitNumByBase[0].charAt(i) < ('0' + base)) ||
                            (splitNumByBase[0].charAt(i) >= 'A' &&
                                    splitNumByBase[0].charAt(i) < ('A' + base - 10))
                    ))
                        return false;
                }
            }
            else
                return false;
        }

        else
            return false;




        ////////////////////
        return ans;
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
        if ((base < 2 || base > 10) && base != 16) {
            return ans;
        }
        int remainder;
        while (num > 0) {
            remainder = num % base;
            if (base == 16) {
                if (remainder == 10) {
                    ans += 'A';
                } else if (remainder == 11) {
                    ans += 'B';
                } else if (remainder == 12) {
                    ans += 'C';
                } else if (remainder == 13) {
                    ans += 'D';
                } else if (remainder == 14) {
                    ans += 'E';
                } else if (remainder == 15) {
                    ans += 'F';
                } else {
                    ans += remainder;
                }
            } else {
                ans += remainder;
            }
            num /= base;
        }
        ans = new StringBuffer(ans).reverse().toString() + "b"+ decimalToChar(base);
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
        ans = number2Int(n1) == number2Int(n2);
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
        if ( arr == null || arr.length == 0 ) return -1; // null or empty
        for ( int i = 1; i < arr.length; i++ )
        {
            if ( number2Int(arr[i]) > number2Int(arr[ans]) ) ans = i;
        }
        return ans; // position of the first largest found
    }

    public static String sumOfNumbers(String num1, String num2, int base) {
        int intNum1 = number2Int(num1) , intNum2 = number2Int(num2);
        String ans = "";
        ans = int2Number(intNum1+intNum2, base);
        return ans+"b"+decimalToChar(base);
    }

    public static String multiOfNumbers(String num1, String num2, int base) {
        int intNum1 = number2Int(num1) , intNum2 = number2Int(num2);
        String ans = "";
        ans = int2Number(intNum1*intNum2, base);
        return ans+"b"+decimalToChar(base);
    }


}