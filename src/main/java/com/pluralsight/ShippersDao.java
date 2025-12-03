package com.pluralsight;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Scanner;

public class ShippersDao {
    private static Scanner scanner = new Scanner(System.in);

    private DataSource dataSource;

    public ShippersDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public int shipperUpdate() {
        String input = ("What is the company name of the shipper you would like to add to the Shipper table?");
        System.out.println(input);
        String shipperName = scanner.nextLine();
        String input2 = ("What is the phone number of the shipper you would like to add to the Shipper table?");
        System.out.println(input2);
        String shipperPhone = scanner.nextLine();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shippers (companyName, phone) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, shipperName);
            preparedStatement.setString(2, shipperPhone);

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

            try (ResultSet keys = preparedStatement.getGeneratedKeys();) {

                boolean results = false;
                while (keys.next()) {
                    results = true;
                    System.out.println("Keys added: " + keys.getInt(1));
                    return keys.getInt(1);
                }if (!results){
                    System.out.println("No results were found");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}
