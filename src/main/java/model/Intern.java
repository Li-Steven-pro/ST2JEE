/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import utils.DataServices;

/**
 *
 * @author NicoSoOl
 */
@Stateless
public class Intern extends Model {

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
    //id
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

    public void setInternById(DataServices dbs,int ident,int missionID){
        try {
            ResultSet internRS = dbs.selectQuery("SELECT * from info_intern WHERE info_intern_id = '" + Integer.toString(ident)+"';");
            if(internRS.next() == true ){
                this.setId(internRS.getInt("info_intern_id"));
                this.setGroup(internRS.getString("intern_group"));
                this.setFirst_name(internRS.getString("firstname"));
                this.setLast_name(internRS.getString("lastname"));
                this.setAddress(internRS.getString("address"));
                this.setSkills(internRS.getString("skills"));
                this.setLinkedin(internRS.getString("linkedin"));
                this.setBirthday(internRS.getDate("birthday"));
                mission = new Mission();
                mission.setMissionById(dbs, missionID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Intern.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
