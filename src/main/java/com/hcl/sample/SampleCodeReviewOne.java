package com.hcl.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.hcl.sample.DatabaseUtil.DB_USER;

public class SampleCodeReviewOne {

    private static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    public static void main(String arg) {

        Boolean isException = false;


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception found");
            isException = true;
            return;
        }

        /**
         * below commented since it is an error
         */
        Connection connection = getDBConnection();
    }


/*

       try {
            return DriverManager.getConnection(DatabaseUtil.getDBConnectionString(), DB_USER, DB_USER);
        } catch (SQLException e) {
            throw new RuntimeException("Exception in Method");
        }
 */
    /**
     * 
     * changed the below method to static to avoid the above error
     */
    private static Connection getDBConnection() throws RuntimeException {
        Connection connection = null;

        try {
            return DriverManager.getConnection(DatabaseUtil.getDBConnectionString(), DB_USER, DB_USER);
        } catch (SQLException e) {
            throw new RuntimeException("Exception in Method");
        }
    }


    private List executeQuery() {
        try {
            String empName = "Edwardo";
            int empId = 51599999;
            String empDept = "Finance";
            int financeCount = 0;

            PreparedStatement pStmt = getDBConnection().prepareStatement("Select empDept From Employee where empId = " + empId);

            ResultSet rs = pStmt.executeQuery("Select empDept from Employee");

            while (rs.next()) {
                if (rs.getString("empDept").equals("Finance")) {
                    financeCount++;
                }
                System.out.println(rs.getInt(1) + " Name: " + rs.getString(2) + ", Dept Avg: " + rs.getLong(3));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return new ArrayList(10);

    }
}

class DatabaseUtil{

	/**
	 * changed the below variable to static to avoid error
	 */
    private final static String DB_URL = "localhost:3306/";
    private static final String DB_SCHEMA = "movies_db";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "password";
	
	//changes for checking stash
    public static String getDBConnectionString(){
        return DB_URL + DB_SCHEMA;
    }
}
