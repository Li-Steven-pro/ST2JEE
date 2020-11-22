/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    // visit_done
    private boolean done; 
}
