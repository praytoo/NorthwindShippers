package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;

import static com.pluralsight.ShipperInput.getNumberChoice;
import static java.lang.System.out;

public class Shippers {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");

        ShippersDao dataManager = new ShippersDao(dataSource);

        boolean endProgram = false;
        while (!endProgram) {
            endProgram = homeScreen(dataManager);
        }
    }

    public static boolean homeScreen(ShippersDao dataManager) {
        String options = ("""
                What do you want to do?
                 1) Add a shipper
                 2) Update a shipper phone number
                 3) Delete a shipper
                 4) View all shippers
                 0) Exit
                Select an option:
                """);
        switch (getNumberChoice(options)) {
            case 1:
                int shippers = dataManager.shipperAdd();
                System.out.println(shippers);
                break;
            case 2:
                int shippersUpdate = dataManager.shipperUpdate();
                System.out.println(shippersUpdate);
                break;
            case 3:
                int shipperDelete = dataManager.shipperDelete();
                System.out.println(shipperDelete);
                break;
            case 4:
                List<Shippers> shippersAll = dataManager.displayAllShippers();
                System.out.println(shippersAll);
                break;
            case 0:
                System.exit(0);
                break;
            default:
                out.println("that's not an option");
                break;
        }
        return false;
    }

    private int shipper_id;
    private String company_name;
    private String phone;

    @Override
    public String toString() {
        return "Shippers\n" +
                "shipper_id = " + shipper_id + "\n" +
                "company_name = " + company_name + "\n" +
                "phone = " + phone + '\n' + "--------\n";
    }

    public Shippers(int shipper_id, String company_name, String phone) {
        this.shipper_id = shipper_id;
        this.company_name = company_name;
        this.phone = phone;
    }
}
