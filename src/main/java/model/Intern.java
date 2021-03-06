/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import utils.DataServices;

/**
 *
 * @author NicoSoOl
 */
@Stateless
public class Intern {

    // Attributs
    private static String table = "info_intern";
    private static String[] attr
            = {"info_intern_id",
                "intern_group",
                "firstname",
                "lastname",
                "address",
                "skills",
                "linkedin",
                "birthday"};
    //info_intern_
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
    //mission
    private Mission mission;

    private DataServices dbs;
    
    private ResultSet internRS;
    
    public Intern() {
        id = -1;
        mission = new Mission();
    }

    public static String getTable() {
        return table;
    }

    public static String[] getAttr() {
        return attr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Mission getMission() {
        return mission;
    }

    //info_intern_id
    public void setMission(Mission mission) {
        this.mission = mission;
    }
    
    /**
    * Set the intern java bean using the id and get param from the db 
    * 
    *@param user Personal db user login
    *@param pwd Personal db user password
    *@param ident intern id
    *@param missionID mission id 
    **/
    public void setInternById(String user, String pwd, int ident, int missionID) {
        try {
            dbs = new DataServices(user, pwd);
            internRS = dbs.selectQuery("SELECT * from info_intern WHERE info_intern_id = '" + Integer.toString(ident) + "';");
            if (internRS.next() == true) {
                this.setId(internRS.getInt("info_intern_id"));
                this.setGroup(internRS.getString("intern_group"));
                this.setFirst_name(internRS.getString("firstname"));
                this.setLast_name(internRS.getString("lastname"));
                this.setAddress(internRS.getString("address"));
                this.setSkills(internRS.getString("skills"));
                this.setLinkedin(internRS.getString("linkedin"));
                this.setBirthday(internRS.getDate("birthday"));
                mission = new Mission();
                mission.setMissionById(user, pwd, missionID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Intern.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ShowConsole() {
        System.out.println("------------------------");
        System.out.println("Intern id :" + this.getId());
        System.out.println("Intern firtname :" + this.getFirst_name());
        System.out.println("Intern lastname :" + this.getLast_name());
        System.out.println("Intern group :" + this.getGroup());
        System.out.println("Intern linkedin :" + this.getLinkedin());

    }
}
