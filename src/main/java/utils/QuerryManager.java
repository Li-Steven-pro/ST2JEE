/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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
        String querry = "INSERT INTO 'ST2EEDB'.'" + tableName + paranthesesAttr(attributs);
        querry += " VALUES " + parantheses(values);
        querry += ";";
        return querry;
    }

    public static String sqlAttr(String attr) {
        return '`' + attr + '`';
    }

    public static String sqlVar(String var) {
        String value;
        if (var == null) {
            value = "NULL";
        } else if (var.toUpperCase().equals("TRUE")) {
            value = "'1'";
        } else if (var.toUpperCase().equals("FALSE")) {
            value = "'0'";
        } else {
            value = "'" + var + "'";
        }
        return value;
    }

    public static String update(String tableName, int ID, ArrayList<String> attributs, ArrayList<String> values) {
        String querry = "UPDATE `ST2EEDB`.`" + tableName + "` SET\n";
        for (int i = 0; i < attributs.size() - 1; i++) {
            querry += sqlAttr(attributs.get(i)) + " = " + sqlVar(values.get(i)) + ",\n";
        }
        if (0 < attributs.size()) {
            querry += sqlAttr(attributs.get(attributs.size() - 1)) + " = ";
            querry += sqlVar(values.get(values.size() - 1)) + "\n";
        }
        querry += "WHERE (`" + tableName + "_id` = '" + ID + "');\n";
        return querry;
    }

    public static String parantheses(ArrayList<String> elements) {
        String par = "(";
        for (int i = 0; i < elements.size() - 1; i++) {
            par += sqlVar(elements.get(i)) + ",";
        }
        if (0 < elements.size()) {
            par += sqlVar(elements.get(elements.size() - 1));
        }
        par += ")";
        return par;
    }

    public static String paranthesesAttr(ArrayList<String> elements) {
        String par = "(";
        for (int i = 0; i < elements.size() - 1; i++) {
            par += sqlAttr(elements.get(i)) + ",";
        }
        if (0 < elements.size()) {
            par += sqlAttr(elements.get(elements.size() - 1));
        }
        par += ")";
        return par;
    }

    public static String paranthesesAttrValues(String[] elements) {
        String par = "(";
        for (int i = 1; i < elements.length - 1; i++) {
            par += sqlAttr(elements[i]) + ",";
        }
        if (1 < elements.length) {
            par += sqlAttr(elements[elements.length - 1]);
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
                + "INSERT INTO visit_sheet " + paranthesesAttrValues(VisitSheet.getAttr()) + "\n"
                + "  VALUES" + parantheses(val_visit_sheet) + ";\n"
                + "SET @visit_sheet_id = LAST_INSERT_ID();\n"
                + "\n"
                + "INSERT INTO eval_sheet " + paranthesesAttrValues(EvalSheet.getAttr()) + " \n"
                + "  VALUES" + parantheses(val_visit_sheet) + ";\n"
                + "SET @eval_sheet_id = LAST_INSERT_ID();\n"
                + "\n"
                + "INSERT INTO mission " + paranthesesAttrValues(Mission.getAttr()) + " \n"
                + "  VALUES" + parantheses(val_mission) + ";\n"
                + "SET @mission_id = LAST_INSERT_ID();\n"
                + "\n"
                + "INSERT INTO info_intern " + paranthesesAttrValues(Intern.getAttr()) + "\n"
                + "  VALUES" + parantheses(val_info_intern) + ";\n"
                + "SET @intern_info_id = LAST_INSERT_ID();\n"
                + "\n"
                + "INSERT INTO intern " + paranthesesAttrValues(Intern.getAttr()) + " \n"
                + "  VALUES" + parantheses(val_intern) + ";\n"
                + "COMMIT;\n";
        return query;
    }

    public static String updateIntern(Intern intern) {
        String query = "START TRANSACTION;\n";

        // Update intern
        ArrayList<String> attributs = new ArrayList(Arrays.asList(Intern.getAttr()));
        attributs.remove(0);
        String[] values = {intern.getGroup(),
            intern.getFirst_name(),
            intern.getLast_name(),
            intern.getAddress(),
            intern.getSkills(),
            intern.getLinkedin(),
            ((Date)intern.getBirthday()).toString(),
            Integer.toString(intern.getMission().getId())};
        ArrayList<String> valuesArray = new ArrayList(Arrays.asList(values));
        query += update(Intern.getTable(), intern.getId(), attributs, valuesArray);

        // Update mission
        Mission mission = intern.getMission();
        attributs = new ArrayList(Arrays.asList(Mission.getAttr()));
        attributs.remove(0);
        String[] valuesMission = {Integer.toString(mission.getYear()),
            ((Date)mission.getStartDate()).toString(),
            ((Date)mission.getEndDate()).toString(),
            mission.getReport_title(),
            mission.getComment(),
            mission.getMeetingInfo(),
            Boolean.toString(mission.isSoutenance()),
            mission.getKeyWord(),
            Integer.toString(mission.getEvalS().getId()),
            Integer.toString(mission.getVisitS().getId())};
        valuesArray = new ArrayList(Arrays.asList(valuesMission));
        query += update(Mission.getTable(), mission.getId(), attributs, valuesArray);

        // Update Eval Sheet
        EvalSheet evalS = mission.getEvalS();
        attributs = new ArrayList(Arrays.asList(EvalSheet.getAttr()));
        attributs.remove(0);
        String[] valuesEvalSheet = {evalS.getComment(),
            Integer.toString(evalS.getGradeTech()),
            Integer.toString(evalS.getGradeCom())};
        valuesArray = new ArrayList(Arrays.asList(valuesEvalSheet));
        query += update(EvalSheet.getTable(), evalS.getId(), attributs, valuesArray);

        // Update Visit Sheet
        VisitSheet visitS = mission.getVisitS();
        attributs = new ArrayList(Arrays.asList(VisitSheet.getAttr()));
        attributs.remove(0);
        String[] valuesVisitSheet = {Boolean.toString(visitS.isPlanned()),
            Boolean.toString(visitS.isDone())};
        valuesArray = new ArrayList(Arrays.asList(valuesVisitSheet));
        query += update(VisitSheet.getTable(), visitS.getId(), attributs, valuesArray);

        query += "COMMIT;\n";
        return query;
    }
}
