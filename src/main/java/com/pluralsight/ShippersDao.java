package com.pluralsight;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShippersDao {
    private static DataSource dataSource;

    public ShippersDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public int shipperAdd() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shippers (companyName, phone) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, ShipperInput.addShipper1());
            preparedStatement.setString(2, ShipperInput.addShipper2());

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

    public List<Shippers> displayAllShippers() {
        List<Shippers> shippers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT shipperID, companyName, phone FROM shippers;");

             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                int shipper_id = resultSet.getInt("shipperID");
                String companyName = resultSet.getString("companyName");
                String phone = resultSet.getString("phone");

                Shippers shippers1 = new Shippers(shipper_id, companyName, phone);
                shippers.add(shippers1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shippers;
    }


    public int shipperUpdate() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shippers SET phone = ?" + "WHERE shipperID = ?;")) {

            preparedStatement.setString(1, ShipperInput.updateShipper2());
            preparedStatement.setString(2, ShipperInput.updateShipper1());

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public int shipperDelete() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shippers WHERE shipperID = ?;")) {

            preparedStatement.setString(1, ShipperInput.deleteShipper());

            int rows = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

}
