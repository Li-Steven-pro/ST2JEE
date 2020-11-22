/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author NicoSoOl
 */
@Stateless
public class Intern extends Model{

    // Attributs
    private static String table = "info_intern";
    private static String[] attr = 
        {"info_intern_id",
        "intern_group",
        "firstname",
        "lastname",
        "address",
        "skills",
        "linkedin",
        "birthday"};
    //info_intern_id
    private int id; 
    //intern_group
    private String group;
    //firstname
    private String first_name;
    //lastname
    private String last_name;
    //address
    private String address;
    //skills
    private String skills;
    //linkedin
    private String linkedin;
    //birthday
    private Date birthday;
    
    private Mission mission;
}
