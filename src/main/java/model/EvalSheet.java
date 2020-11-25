/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataServices;

/**
 *
 * @author steve
 */
public class EvalSheet {

    static private String table = "eval_sheet";
    private static String[] attr
            = {"eval_sheet_id",
                "comments_of_supervisor",
                "grade_tech",
                "eval_sheet_done"};
    // eval_sheet_id
    private int id;
    // comments_of_supervisor
    private String comment;
    // grade_tech
    private int gradeTech;
    // grade_com
    private int gradeCom;

    private boolean done;
    // eval_sheet_done

    public void setEvalSheetById(DataServices dbs, int ident) {
        try {
            ResultSet evalsheetRS = dbs.selectQuery("SELECT * from " + EvalSheet.getTable() + " WHERE eval_sheet_id = '" + Integer.toString(ident) + "';");
            if (evalsheetRS.next() == true) {
                this.setId(evalsheetRS.getInt("eval_sheet_id"));
                this.setComment(evalsheetRS.getString("comments_of_supervisor"));
                this.setGradeTech(evalsheetRS.getInt("grade_tech"));
                this.setGradeCom(evalsheetRS.getInt("grade_com"));
                this.setDone(evalsheetRS.getBoolean("eval_sheet_done"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Intern.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGradeTech() {
        return gradeTech;
    }

    public void setGradeTech(int gradeTech) {
        this.gradeTech = gradeTech;
    }

    public int getGradeCom() {
        return gradeCom;
    }

    public void setGradeCom(int gradeCom) {
        this.gradeCom = gradeCom;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
