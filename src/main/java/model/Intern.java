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
    private String group;
    private String first_name;
    private String last_name;
    private Boolean CdC;    // ???
    private Boolean visit_sheet;
    private Boolean eval_sheet_done;
    private Boolean sondageWeb; // ???
    private Boolean report_done;
    private Boolean soutenance;
    private Boolean visit_planned;
    private Boolean visit_done;
    private Date start_mission;
    private Date end_mission;
    // ENTR ???
    // MdS ???
    private String address;

    // Getters & Setters
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

    public Boolean getCdC() {
        return CdC;
    }

    public void setCdC(Boolean CdC) {
        this.CdC = CdC;
    }

    public Boolean getVisit_sheet() {
        return visit_sheet;
    }

    public void setVisit_sheet(Boolean visit_sheet) {
        this.visit_sheet = visit_sheet;
    }

    public Boolean getEval_sheet_done() {
        return eval_sheet_done;
    }

    public void setEval_sheet_done(Boolean eval_sheet_done) {
        this.eval_sheet_done = eval_sheet_done;
    }

    public Boolean getSondageWeb() {
        return sondageWeb;
    }

    public void setSondageWeb(Boolean sondageWeb) {
        this.sondageWeb = sondageWeb;
    }

    public Boolean getReport_done() {
        return report_done;
    }

    public void setReport_done(Boolean report_done) {
        this.report_done = report_done;
    }

    public Boolean getSoutenance() {
        return soutenance;
    }

    public void setSoutenance(Boolean soutenance) {
        this.soutenance = soutenance;
    }

    public Boolean getVisit_planned() {
        return visit_planned;
    }

    public void setVisit_planned(Boolean visit_planned) {
        this.visit_planned = visit_planned;
    }

    public Boolean getVisit_done() {
        return visit_done;
    }

    public void setVisit_done(Boolean visit_done) {
        this.visit_done = visit_done;
    }

    public Date getStart_mission() {
        return start_mission;
    }

    public void setStart_mission(Date start_mission) {
        this.start_mission = start_mission;
    }

    public Date getEnd_mission() {
        return end_mission;
    }

    public void setEnd_mission(Date end_mission) {
        this.end_mission = end_mission;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
