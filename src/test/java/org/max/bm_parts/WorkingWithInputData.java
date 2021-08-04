package org.max.bm_parts;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkingWithInputData {

    private String url = "jdbc:mysql://localhost:3306/shop";
    private String user = "root";
    private String password = "root";
    private Connection connection;

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connection = DriverManager.getConnection(
                    url, user, password);
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException {

        WorkingWithInputData workingWithInputData = new WorkingWithInputData();
        // workWithBD.deleteAllObjects();
        // workWithBD.getObjectsBySkuNumber("KD45549");
        //    workWithBD.getAllObjects();
        //       workWithBD.deleteAllObjects();
        workingWithInputData.deleteAllObjects();
//        workWithBD.updateObject(new InputData(0,"https://b2b.bm.parts/", "ACB35X50X20",
//               "VIP", 1000, 27.8556, 33.091665, LocalDateTime
//                .parse("2018-11-03T12:45:30")));

    }

    public List<InputData> getAllObjects() throws SQLException {

        connect();
        String a="ggg";
        List<InputData> allobjects = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM input_data");
        while (rs.next()) {
            
            int id = rs.getInt("id");
            String artikul = rs.getString("skuNumber");
            String brand = rs.getString("brandName");
            LocalDateTime date =  rs.getTimestamp("created").toLocalDateTime();
            
            InputData inputData  = new InputData(id,  artikul, brand, 
                 date);
            allobjects.add(inputData);

        }
        statement.close();
        disconnect();
        return allobjects;
    }

    public List<InputData> getObjectsBySkuNumber(String sku_Number) throws SQLException {

        connect();
        List<InputData> allobjects = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM input_data WHERE skuNumber=?");
        preparedStatement.setString(1, sku_Number);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String pathname = rs.getString("pageName");
            String artikul = rs.getString("skuNumber");
            String brand = rs.getString("brandName");
            double price = rs.getDouble("price");
            double euroRate = rs.getDouble("euroAveCourse");
            double usdRate = rs.getDouble("usdAveCourse");
            LocalDateTime date =  rs.getTimestamp("created").toLocalDateTime();
            InputData inputData = new InputData(id, artikul, brand, date);
            allobjects.add(inputData);
            System.out.println(inputData);
        }
        rs.close();
        disconnect();

        return allobjects;
    }

    public void insertNewObject(InputData art) throws SQLException {


        String sql = "INSERT INTO input_data ( skuNumber, brandName,created) VALUES (?, ?, ?) ";
        connect();
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1,art.getSkuNumber());
        statement.setString(2,art.getBrandName());
        Timestamp created = Timestamp.valueOf(art.getCreated());
        statement.setTimestamp(3,created);


        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

    }

    public void updateObject(InputData InputData) throws SQLException {

        String sql = "UPDATE input_data SET   brandName= ?,  created=? " +
                " WHERE skuNumber= ?";
        connect();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,InputData.getBrandName());
        Timestamp created = Timestamp.valueOf(InputData.getCreated());
        statement.setTimestamp(2,created);
        statement.setString(3,InputData.getSkuNumber());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
    }

    public void deleteAllObjects() throws SQLException {

        connect();
        String sql = "DELETE FROM input_data";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

    public void deleteObjectsBySkuNumber(String sku_Number) throws SQLException {

        connect();
        String sql = "DELETE FROM input_data WHERE skuNumber=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,sku_Number);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }
}
