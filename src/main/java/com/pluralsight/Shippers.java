package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class Shippers {
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");

        ShippersDao dataManager = new ShippersDao(dataSource);

        int shippers = dataManager.shipperUpdate();

        System.out.println(shippers);
    }
}
