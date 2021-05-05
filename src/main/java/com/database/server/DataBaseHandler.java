package com.database.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseHandler extends Configurations {
    private Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException,SQLException{
        String connection = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connection,dbUser,dbPassword);

        return dbConnection;
    }

    public void sign(String city, String info){
        String insert = "INSERT INFO:" + Constants.CITY_TABLE + "(" + Constants.CITY_NAME + ", " + Constants.CITY_INFO + ")"+
        "VALUES(?,?)";

        try {
            PreparedStatement prepared  = getDbConnection().prepareStatement(insert);
            prepared.setString(1,city);
            prepared.setString(2,info);
            prepared.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }
}
