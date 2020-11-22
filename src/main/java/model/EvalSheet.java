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
public class EvalSheet {
    static private String table = "eval_sheet";
    private static String[] attr = 
        {"eval_sheet_id",
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
    // eval_sheet_done
    private boolean done;
}
