package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input: ");
        String input = scanner.nextLine();
        String result = calc(input);
        System.out.println("Output " + result);
    }


    public static String calc(String input) throws Exception {
        String[] symbol = input.split(" ");

        Set<String> operators = new HashSet<>(List.of("+", "-", "*", "/")); 
        if (!operators.contains(symbol[1])) {
            throw new Exception("Incorrect operand");
        }



        if (symbol.length != 3) throw new Exception("Incorrect values");
        String operator = detectOperator(symbol[1]);

        String leftValueStr = symbol[0];
        String rightValueStr = symbol[2];
        int leftValue, rightValue, result = 0;
        int flag = 0;

        if (isArabNumber(leftValueStr) && isArabNumber(rightValueStr)) {
            leftValue = Integer.parseInt(leftValueStr);
            rightValue = Integer.parseInt(rightValueStr);
        } else if (!isArabNumber(leftValueStr) && !isArabNumber(rightValueStr)) {
            leftValue = RomNum.convertRimToArabic(leftValueStr);
            rightValue = RomNum.convertRimToArabic(rightValueStr);
            flag = 1;
        }  else {
            throw new Exception("Only one number system");
        }

        if ((rightValue <= 0 || rightValue > 10) || (leftValue <= 0 || leftValue > 10)) {
            throw new Exception("only 1,2,3,4,5,6,7,8,9,10");
        }

        if(flag == 1){
            result = Integer.parseInt(calculate(leftValue,rightValue,operator));
            if (result < 1) throw new Exception();
            return RomNum.convertArabToRim(result);
        }
        return (calculate(leftValue,rightValue, operator));
    }

    static String calculate(int leftValue, int rightValue, String operator) throws Exception {
        if(operator.equals("+")){
            return leftValue + rightValue + "";
        } else if (operator.equals("-")) {
            return leftValue - rightValue + "";
        } else if (operator.equals("*")) {
            return leftValue * rightValue + "";
        } else if (operator.equals("/") ) {
            return leftValue / rightValue + "";
        } else {
            throw new Exception("only + - * /");
        }
    }

    static String detectOperator(String input) {
        if (input.contains("+")) {
            return "+";
        } else if (input.contains("-")) {
            return "-";
        } else if (input.contains("*")) {
            return "*";
        } else if (input.contains("/")) {
            return "/";
        } else {
            return null;
        }
    }



    static boolean isArabNumber (String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


class RomNum {
    private static String[] romanNumbers = {
            "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    public static int convertRimToArabic(String input) {
        int result = 0;

        for (int i = 0; i < romanNumbers.length; i++) {
            if (input.equals(romanNumbers[i])) {
                result = i;
            }
        }
        return result;
    }

    public static String convertArabToRim(int result) {

        for (int i = 0; i < romanNumbers.length; i++) {
            if (result == i) {
                return String.valueOf(romanNumbers[i]);
            }
        }
        return String.valueOf(result);
    }
}
