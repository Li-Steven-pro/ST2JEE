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
import model.Intern;

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

    public ResultSet selectQuery(String query) {
        ResultSet rs = null;
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = this.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int modifQuery(String query) {
        int rs = 0;
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
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
