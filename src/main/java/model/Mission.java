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
public class Mission {
    
    private static String table = "info_intern";
    private static String[] attr = 
        {"mission_id",
        "year",
        "start_mission",
        "end_mission",
        "report_title",
        "comments_of_the_intern",
        "mid_internship_meeting_info",
        "soutenance",
        "keyWord",
        "eval_sheet_id",
        "visit_sheet_id"};
    
    // mission_id
    private int id;
    // year
    private int year;
    // start_mission
    private String startDate;
    // end_mission
    private String endDate;
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
    // visit_sheet_id
    private EvalSheet EvalS;
    private VisitSheet VisitS;
    
    
}
