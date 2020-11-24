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
public class VisitSheet {
    static private String table = "visit_sheet";
    private static String[] attr = 
        {"visit_sheet_id",
        "visit_planned",
        "visit_done"};
    // visit_sheet_id
    private int id;
    // visit_planned
    private boolean planned;
    
    public void setVisitSheetById(DataServices dbs,int ident){
        try {
            ResultSet visitsheetRS = dbs.selectQuery("SELECT * from " + VisitSheet.getTable() +" WHERE visit_sheet_id = '" + Integer.toString(ident)+"';");
            if(visitsheetRS.next() == true ){
                this.setId(visitsheetRS.getInt("visit_sheet_id"));
                this.setPlanned(visitsheetRS.getBoolean("visit_planned"));
                this.setDone(visitsheetRS.getBoolean("visit_done"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Intern.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPlanned() {
        return planned;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public static String getTable() {
        return table;
    }
    // visit_done
    private boolean done; 
}
