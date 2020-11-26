/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataServices;

/**
 *
 * @author steve
 */
public class Mission {

    private static String table = "mission";
    private static String[] attr
            = {"mission_id",
                "year",
                "start_mission",
                "end_mission",
                "report_title",
                "comments_of_the_intern",
                "mid_internship_meeting_info",
                "soutenance",
                "key_word",
                "eval_sheet_id",
                "visit_sheet_id"};

    // mission_id
    private int id;
    // year
    private int year;
    // start_mission
    private Date startDate;
    // end_mission
    private Date endDate;
    // report_title
    private String report_title;
    // comments_of_the_intern
    private String comment;
    // mid_internship_meeting_info
    private String meetingInfo;
    // soutenance
    private boolean soutenance;
    // key_word
    private String keyWord;
    // eval_sheet_id
    private EvalSheet evalS;
    // visit_sheet_id
    private VisitSheet visitS;

    public static String[] getAttr() {
        return attr;
    }
    public Mission(){
        id = -1;
        evalS = new EvalSheet();
        visitS = new VisitSheet();
    }
    public void setMissionById(DataServices dbs, int ident) {
        try {
            String query = "SELECT * from " + Mission.getTable() + " WHERE mission_id = '" + Integer.toString(ident) + "';";
            ResultSet missionRS = dbs.selectQuery(query);
            if (missionRS.next() == true) {
                this.setId(missionRS.getInt("mission_id"));
                this.setYear(missionRS.getInt("year"));
                this.setStartDate(missionRS.getDate("start_mission"));
                this.setEndDate(missionRS.getDate("end_mission"));
                this.setReport_title(missionRS.getString("report_title"));
                this.setComment(missionRS.getString("comments_of_the_intern"));
                this.setSoutenance(missionRS.getBoolean("soutenance"));
                this.setKeyWord(missionRS.getString("key_word"));
                if (missionRS.getObject("eval_sheet_id") != null) {
                    evalS = new EvalSheet();
                    evalS.setEvalSheetById(dbs, missionRS.getInt("eval_sheet_id"));
                } else {
                    evalS = new EvalSheet();
                }
                if (missionRS.getObject("visit_sheet_id") != null) {
                    visitS = new VisitSheet();
                    visitS.setVisitSheetById(dbs, missionRS.getInt("visit_sheet_id"));
                } else {
                    visitS = new VisitSheet();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Intern.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getTable() {
        return table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReport_title() {
        return report_title;
    }

    public void setReport_title(String report_title) {
        this.report_title = report_title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMeetingInfo() {
        return meetingInfo;
    }

    public void setMeetingInfo(String meetingInfo) {
        this.meetingInfo = meetingInfo;
    }

    public boolean isSoutenance() {
        return soutenance;
    }

    public void setSoutenance(boolean soutenance) {
        this.soutenance = soutenance;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public EvalSheet getEvalS() {
        return evalS;
    }

    public void setEvalS(EvalSheet EvalS) {
        this.evalS = EvalS;
    }

    public VisitSheet getVisitS() {
        return visitS;
    }

    public void setVisitS(VisitSheet VisitS) {
        this.visitS = VisitS;
    }
}
