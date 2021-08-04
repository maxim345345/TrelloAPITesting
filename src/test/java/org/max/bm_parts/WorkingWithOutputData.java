package org.max.bm_parts;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkingWithOutputData {

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

    public WorkingWithOutputData() {
    }

    public WorkingWithOutputData(String url, String user, String password, Connection connection) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connection = connection;
    }

    public static void main(String[] args) throws SQLException {

        WorkingWithOutputData workingWithOutputData = new WorkingWithOutputData();
       // workWithBD.deleteAllObjects();
       // workWithBD.getObjectsBySkuNumber("KD45549");
    //    workWithBD.getAllObjects();
 //       workWithBD.deleteAllObjects();
        workingWithOutputData.deleteAllObjects();
//        workWithBD.updateObject(new OpponentPrice(0,"https://b2b.bm.parts/", "ACB35X50X20",
//               "VIP", 1000, 27.8556, 33.091665, LocalDateTime
//                .parse("2018-11-03T12:45:30")));

    }

    public  List<OpponentPrice> getAllObjects() throws SQLException {

        connect();
        String a="ggg";
        List<OpponentPrice> allobjects = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM output_data");
        while (rs.next()) {
            int id = rs.getInt("id");
            String pathname = rs.getString("pageName");
            String artikul = rs.getString("skuNumber");
            String brand = rs.getString("brandName");
            double price = rs.getDouble("price");
            double euroRate = rs.getDouble("euroAveCourse");
            double usdRate = rs.getDouble("usdAveCourse");
          ;
            LocalDateTime date =  rs.getTimestamp("created").toLocalDateTime();

            OpponentPrice opponentPrice = new OpponentPrice(id, pathname, artikul, brand, price,
                     euroRate, usdRate, date);
            allobjects.add(opponentPrice);

        }
        statement.close();
        disconnect();
        return allobjects;
    }

    public List<OpponentPrice> getObjectsBySkuNumber(String sku_Number) throws SQLException {

        connect();
        List<OpponentPrice> allobjects = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM output_data WHERE skuNumber=?");
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
            OpponentPrice opponentPrice = new OpponentPrice(id, pathname, artikul, brand, price,
                     euroRate, usdRate, date);
            allobjects.add(opponentPrice);
            System.out.println(opponentPrice);
        }
        rs.close();
        disconnect();

        return allobjects;
    }

    public void insertNewObject(OpponentPrice art) throws SQLException {


        String sql = "INSERT INTO output_data (pageName, " +
                "skuNumber, brandName,price,euroAveCourse,usdAveCourse,created) VALUES (?, ?, ?,?, ?, ?,?) ";
        connect();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,art.getPageName());
        statement.setString(2,art.getSkuNumber());
        statement.setString(3,art.getBrandName());
        statement.setDouble(4,art.getPrice());
        statement.setDouble(5,art.getEuroAveCourse());
        statement.setDouble(6,art.getUsdAveCourse());
        Timestamp created = Timestamp.valueOf(art.getCreated());
        statement.setTimestamp(7,created);


        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

    }

    public void updateObject(OpponentPrice opponentPrice) throws SQLException {

        String sql = "UPDATE output_data SET pageName = ?,  brandName= ?, price=?, euroAveCourse=?, usdAveCourse=?, created=? " +
                " WHERE skuNumber= ?";
        connect();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,opponentPrice.getPageName());
        statement.setString(2,opponentPrice.getBrandName());
        statement.setDouble(3,opponentPrice.getPrice());
        statement.setDouble(5,opponentPrice.getEuroAveCourse());
        statement.setDouble(4,opponentPrice.getUsdAveCourse());
        Timestamp created = Timestamp.valueOf(opponentPrice.getCreated());
        statement.setTimestamp(6,created);
        statement.setString(7,opponentPrice.getSkuNumber());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
    }

    public void deleteAllObjects() throws SQLException {

        connect();
        String sql = "DELETE FROM output_data";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

    public void deleteObjectsBySkuNumber(String sku_Number) throws SQLException {

        connect();
        String sql = "DELETE FROM output_data WHERE skuNumber=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,sku_Number);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

//    public void deleteObject(OpponentPrice opponentPrice) throws SQLException {
//
//        connect();
//        String a = "DELETE FROM bm_parts WHERE skuNumber=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(a);
//        preparedStatement.setString(1, sku_Number);
//        preparedStatement.executeUpdate();
//        preparedStatement.close();
//        disconnect();
//    }




}
