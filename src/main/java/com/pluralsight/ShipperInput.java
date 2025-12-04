package com.pluralsight;


import java.util.Scanner;

import static java.lang.System.out;

public class ShipperInput {
    private static Scanner scanner = new Scanner(System.in);

    public static String addShipper1(){
        String input = ("What is the company name of the shipper you would like to add to the Shipper table?");
        System.out.println(input);
        return scanner.nextLine();
    }
    public static String addShipper2(){
        String input2 = ("What is the phone number of the shipper you would like to add to the Shipper table?");
        System.out.println(input2);
        return scanner.nextLine();
    }

    public static String updateShipper1(){
        String input = ("What is the shipper id of the shipper you would like to update the phone number of in the Shipper table?");
        System.out.println(input);
        return scanner.nextLine();
    }

    public static String updateShipper2(){
        String input2 = ("What is the phone number of the shipper you would like to update the phone number of in the Shipper table?");
        System.out.println(input2);
        return scanner.nextLine();
    }

    public static String deleteShipper(){
        String input = ("What is the shipper id of the shipper you would like to delete from the Shipper table?");
        System.out.println(input);
        return scanner.nextLine();
    }

    public static int getNumberChoice(String options) {
        out.println(options);
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

}
