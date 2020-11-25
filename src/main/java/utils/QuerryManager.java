/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import model.EvalSheet;
import model.Intern;
import model.Mission;
import model.VisitSheet;

/**
 *
 * @author NicoSoOl
 */
public class QuerryManager {

    public static String insertQuerry(String tableName, ArrayList<String> attributs, ArrayList<String> values) {
        String querry = "INSERT INTO 'ST2EEDB'.'" + tableName + parantheses(attributs);
        querry += " VALUES " + parantheses(values);
        querry += ";";
        return querry;
    }

    public static String update(String tableName, int ID, ArrayList<String> attributs, ArrayList<String> values) {
        String querry = "UPDATE 'ST2EEDB'.'" + tableName + "' SET\n";
        for (int i = 0; i < attributs.size() - 1; i++) {
            querry += "'" + attributs.get(i) + "' = '" + values.get(i) + "',\n";
        }
        if (0 < attributs.size()) {
            querry += "'" + attributs.get(attributs.size() - 1) + "' = ";
            querry += "'" + values.get(values.size() - 1) + "'\n";
        }
        querry += "WHERE ('ID' = '" + ID + "');";
        return querry;
    }

    public static String parantheses(ArrayList<String> elements) {
        String par = "(";
        for (int i = 0; i < elements.size() - 1; i++) {
            par += "'" + elements.get(i) + "',";
        }
        if (0 < elements.size()) {
            par += "'" + elements.get(elements.size() - 1) + "'";
        }
        par += ")";
        return par;
    }
    
    public static String paranthesesValues(String[] elements) {
        String par = "(";
        for (int i = 1; i < elements.length - 1; i++) {
            par += "'" + elements[i] + "',";
        }
        if (1 < elements.length) {
            par += "'" + elements[elements.length - 1] + "'";
        }
        par += ")";
        return par;
    }

    public static String transaction(
            ArrayList<String> val_visit_sheet,
            ArrayList<String> val_eval_sheet,
            ArrayList<String> val_mission,
            ArrayList<String> val_intern,
            ArrayList<String> val_info_intern) {
        String query = "START TRANSACTION;\n"
                + "INSERT INTO visit_sheet " + paranthesesValues(VisitSheet.getAttr()) + "\n"
                + "  VALUES" + parantheses(val_visit_sheet) + ";\n"
                + "SET @visit_sheet_id = LAST_INSERT_ID();\n"
                + "\n"
                + "INSERT INTO eval_sheet " + paranthesesValues(EvalSheet.getAttr()) + " \n"
                + "  VALUES" + parantheses(val_visit_sheet) + ";\n"
                + "SET @eval_sheet_id = LAST_INSERT_ID();\n"
                + "\n"
                + "INSERT INTO mission " + paranthesesValues(Mission.getAttr()) + " \n"
                + "  VALUES" + parantheses(val_mission) + ";\n"
                + "SET @mission_id = LAST_INSERT_ID();\n"
                + "\n"
                + "INSERT INTO info_intern " + paranthesesValues(Intern.getAttr()) + "\n"
                + "  VALUES" + parantheses(val_info_intern) + ";\n"
                + "SET @intern_info_id = LAST_INSERT_ID();\n"
                + "\n"
                + "INSERT INTO intern " + paranthesesValues(Intern.getAttr()) + " \n"
                + "  VALUES" + parantheses(val_intern) + ";\n"
                + "COMMIT;";
        return query;
    }
}
