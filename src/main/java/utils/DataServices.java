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
import javax.servlet.http.HttpServlet;

/**
 *
 * @author steve
 */
public class DataServices {
    private Properties prop;
    private InputStream is = null;
    //private ClassLoader cl;
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    
    private String url;
    private String user;
    private String pwd;
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    public void setPwd(String pwd) {
//        this.pwd = pwd;
//    }
//    
    
    public Connection getConnection(){
        try{
           
            prop = new Properties();
            //cl = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/db.properties");
            prop.load(inputStream);
            //is = cont.getServletContext().getResourceAsStream("/WEB-INF/db.properties");
            //prop.load(is);
            
            url = prop.getProperty("db_url");
            user = prop.getProperty("db_user");
            pwd = prop.getProperty("db_pwd");
            System.out.println(url);
            //conn = DriverManager.getConnection(url,user,pwd);
            //stmt = conn.createStatement();
            //rs = stmt.executeQuery("SELECT * from FRIENDS");
            return conn;
        }
        catch (IOException ex) {
                Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
