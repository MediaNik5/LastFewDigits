package org.medianik.digitsofpower;

import java.util.Scanner;

public class Main {

    static Scanner in;

    public static void main(String[] ignored){
        in = new Scanner(System.in);

        long number, power, step, maxPowerOrNumber;
        int module, limitOfDigits, width, widthSeparating;

        log("This is the program, that evaluates few last symbols of number n^p mod q with different n OR p.");
        log("----------------------------");

        log("Please enter the number n: ");
        number = readLong();
        log("Please enter the power p: ");
        power = readLong();
        log("Pass the module number q: ");
        module = readInt();

        log("Now pass the step of increment: ");
        step = readLong();

        log("Now enter how many last symbols would you like to see: ");
        limitOfDigits = readInt();
        log("How many columns will be in the table: ");
        width = readInt();
        log("Enter how many lines are being separated: ");
        widthSeparating = readInt();

        log("Would you like to increase POWER p or NUMBER n?");
        log("Type 'true' if you have chosen power, else type 'false'.");
        boolean isStepPowerElseNumber = readBoolean();
        log(String.format("Pass the max %s: ", isStepPowerElseNumber ? "power" : "number"));
        maxPowerOrNumber = readLong();

        String out = new LastDigitOfPower(
                number,
                power,
                module,
                step,
                maxPowerOrNumber,
                limitOfDigits,
                width,
                widthSeparating,
                isStepPowerElseNumber
        ).toString();

        log(out);
    }

    public static long readLong(){
        return Long.parseLong(in.nextLine());
    }
    public static int readInt(){
        return Integer.parseInt(in.nextLine());
    }
    public static boolean readBoolean(){
        return Boolean.parseBoolean(in.nextLine());
    }

    public static <E> void log(E o){
        System.out.println(o);
    }
}
