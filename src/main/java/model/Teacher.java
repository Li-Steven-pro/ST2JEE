/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author NicoSoOl
 */
@Stateless
public class Teacher extends Model{

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
    
    public boolean access(){
        // Process to know if the user is in the db
        if(true){
            have_access = true;
        }
        return have_access;
    }
    
    public ArrayList<Intern> getAllInterns(){
        ArrayList<Intern> internsList = new ArrayList();
        return internsList;
    }
}
