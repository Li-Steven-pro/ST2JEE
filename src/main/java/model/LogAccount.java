/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Stateless;

/**
 *
 * @author NicoSoOl
 */
@Stateless
public class LogAccount {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private String user;
    private String pwd;
    private String first_name;
    private String last_name;
    
    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }
    
    public String getPwd(){
        return pwd;
    }
    public void setPwd(String pwd){
        this.pwd = pwd;
    }
    
    public String getFirstName(){
        return first_name;
    }
    public void setFirstName(String first_name){
        this.first_name = first_name;
    }
    
    public String getLastName(){
        return last_name;
    }
    public void setLastName(String last_name){
        this.last_name = last_name;
    }
}
