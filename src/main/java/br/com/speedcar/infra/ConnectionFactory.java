package br.com.speedcar.infra;

import java.sql.*;
import java.lang.Class;

public class ConnectionFactory {

    private ConnectionFactory(){}

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            //return DriverManager.getConnection("jdbc:postgresql://localhost/SpeedCar", "postgres", "1324");
            return DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/kuweqczc", "kuweqczc", "ABBfYUDf8lOE_xaTYJxLGFmjcTwn1JjV");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
