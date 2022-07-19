package com.cydeo.utililities;

import java.sql.*;

public class DBUtils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createConnection(){
        String dbUrl = "jdbc:oracle:thin:@3.83.123.243:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        try{
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void createConnection(String dbUrl, String dbUsername, String dbPassword){

        try{
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void  destroy(){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
