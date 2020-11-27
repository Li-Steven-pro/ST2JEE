/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author steve
 */
public class DataServices {

    private Properties prop;
    private InputStream is = null;

    private Connection conn;

    private String url;
    private String user;
    private String pwd;

    public String getUser() {
        return user;
    }

    /**
     * Constructor
     *
     * @param user The database user login
     * @param pwd The database user password
     */
    public DataServices(String user, String pwd) {
        try {
            prop = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/db.properties");
            prop.load(inputStream);

            url = prop.getProperty("db_url");
            this.user = user;
            this.pwd = pwd;
        } catch (IOException ex) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Connect to database.
     *
     * @return a database connection using user & pwd
     */
    public Connection getConnection() {
        try {
            // Add driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pwd);
            return conn;
        } catch (/*IOException |*/SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     * Execute a SELECT Query
     *
     * @param query the SELECT query to execute
     * @return the result of the SELECT query
     */
    public ResultSet selectQuery(String query) {
        ResultSet rs = null;
        // Clean connexion
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Attempt connexion
        try {
            conn = this.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     * Execute a Query to change the database (INSERT, UPDATE, ...)
     *
     * @param query the Query to execute
     * @return the number of row affected
     */
    public int modifQuery(String query) {
        int rs = 0;
        // Clean connexion
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Attempt connexion
        try {
            conn = this.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
