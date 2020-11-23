/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import utils.DataServices;

/**
 *
 * @author NicoSoOl
 */
@Stateless
public class Teacher extends Model {

    // Attributs
    private String user;
    private String pwd;
    private String first_name;
    private String last_name;
    boolean have_access = false;

    // Getters & Setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean access() {
//        try {
            DataServices dbs = new DataServices(user, pwd);
//            ResultSet rs = dbs.selectQuery("SELECT * from teacher WHERE login = '" + user + "' AND password = '" + pwd + "';");
//            if (rs.next()) {
//                have_access = true;
//            }
            if (dbs.getConnection() != null) {
                have_access = true;
            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
//        }
        System.out.println("Access : " + have_access);
        return have_access;
    }

    public ArrayList<Intern> getAllInterns() {
        ArrayList<Intern> internsList = new ArrayList();
        DataServices dbs = new DataServices(user, pwd);
        ResultSet rs = dbs.selectQuery("SELECT * from Affichage");
        try {
            while (rs.next()) {
                for (int i = 1; i <= 5; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return internsList;
    }

}
