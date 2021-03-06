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

    /**
     * Generate an INSERT query
     *
     * @param tableName The name of the table we want to INSERT in
     * @param attributs The list of the table attributs names
     * @param values The values to INSERT into the table
     * @return an insert query in String format
     */
    public static String insertQuery(String tableName, ArrayList<String> attributs, ArrayList<String> values) {
        String query = "INSERT INTO `ST2EEDB`.`" + tableName + "`" + paranthesesAttr(attributs) + "\n";
        query += " VALUES " + parantheses(values);
        query += ";\nSET @" + tableName + "_id = LAST_INSERT_ID();";
        return query;
    }

    /**
     * Generate an UPDATE query
     *
     * @param tableName The name of the table we want to UPDATE
     * @param ID The id of the row to UPDATE
     * @param attributs The list of the table attributs names
     * @param values The UPDATEd values to insert in the table
     * @return an update query in String format
     */
    public static String update(String tableName, int ID, ArrayList<String> attributs, ArrayList<String> values) {
        String querry = "UPDATE `ST2EEDB`.`" + tableName + "` SET\n";
        for (int i = 0; i < attributs.size() - 1; i++) {
            querry += sqlAttr(attributs.get(i)) + " = " + sqlVar(values.get(i)) + ",\n";
        }
        if (0 < attributs.size()) {
            querry += sqlAttr(attributs.get(attributs.size() - 1)) + " = ";
            querry += sqlVar(values.get(attributs.size() - 1)) + "\n";
        }
        querry += "WHERE (`" + tableName + "_id` = '" + ID + "');\n";
        return querry;
    }

    /**
     * Generate an UPDATE/INSERT query depending if the row id is valide (ID>0)
     *
     * @param tableName The name of the table we want to UPDATE
     * @param ID The id of the row to UPDATE
     * @param attributs The list of the table attributs names
     * @param values The UPDATEd values to insert in the table
     * @return an update query in String format
     */
    public static String updateInsert(String tableName, int ID, ArrayList<String> attributs, ArrayList<String> values) {
        if (ID < 0) {
            return insertQuery(tableName, attributs, values);
        } else {
            return update(tableName, ID, attributs, values);
        }
    }

    /**
     * Format a variable into an sql attribut
     *
     * @param attr the variable
     * @return `attr` if not an sql variable, attr else
     */
    public static String sqlAttr(String attr) {
        if (attr.charAt(0) != '@') {
            return '`' + attr + '`';
        } else {
            return attr;
        }
    }

    /**
     * Format a variable in a way SQL can understand it
     *
     * @param var The variable to format
     * @return the variable formatted for SQL as String
     */
    public static String sqlVar(String var) {
        String value = var;
        if (var == null) {
            value = "NULL";
        } else if (var.toUpperCase().equals("TRUE")) {
            value = "'1'";
        } else if (var.toUpperCase().equals("FALSE")) {
            value = "'0'";
        } else if (var.length() == 0) {
            value = "''";
        } else if (var.charAt(0) != '@') {
            value = "'" + var + "'";
        }
        return value;
    }

    /**
     * If a row don't exist (id<0), replace the id by a SQL variable
     *
     * @param tableName The name of the table
     * @param id The id of the row
     * @return id if valid, "tableName"_id if not
     */
    public static String sqlSetId(String tableName, int id) {
        if (id < 0) {
            return "@" + tableName + "_id";
        }
        return Integer.toString(id);
    }

    /**
     * Convert a sql.date to String, return null if null. Used to avoid
     * exceptions.
     *
     * @param date The date to convert
     * @return the date in string format if not null, else null
     */
    public static String sqlDateToString(Date date) {
        if (date == null) {
            return null;
        }
        return date.toString();
    }

    /**
     * Surround by parantheses a list of elements
     *
     * @param elements The list of attributs
     * @return a String like this: (element[1], elements[2], ...)
     */
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

    /**
     * Surround by parantheses a list of elements in ` `
     *
     * @param elements The list of attributs
     * @return a String like this: (`element[1]`, `elements[2]`, ...)
     */
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

    /**
     * Generate a Transaction Query to update an internship info. Create lacking
     * rows when necessary.
     *
     * @param intern the internship to update
     * @return the Transaction Query to update an internship as a String
     */
    public static String updateIntern(Intern intern) {
        ArrayList<String> attributs;
        ArrayList<String> valuesArray;
        Mission mission = intern.getMission();
        EvalSheet evalS = mission.getEvalS();
        VisitSheet visitS = mission.getVisitS();
        // Start Query
        String query = "START TRANSACTION;\n";
        // Update Eval Sheet
        attributs = new ArrayList(Arrays.asList(EvalSheet.getAttr()));
        attributs.remove(0);
        String[] valuesEvalSheet = {evalS.getComment(),
            Integer.toString(evalS.getGradeTech()),
            Integer.toString(evalS.getGradeCom()),
            Boolean.toString(evalS.isDone())};
        valuesArray = new ArrayList(Arrays.asList(valuesEvalSheet));
        query += updateInsert(EvalSheet.getTable(), evalS.getId(), attributs, valuesArray);

        // Update Visit Sheet
        attributs = new ArrayList(Arrays.asList(VisitSheet.getAttr()));
        attributs.remove(0);
        String[] valuesVisitSheet = {Boolean.toString(visitS.isPlanned()),
            Boolean.toString(visitS.isDone())};
        valuesArray = new ArrayList(Arrays.asList(valuesVisitSheet));
        query += updateInsert(VisitSheet.getTable(), visitS.getId(), attributs, valuesArray);

        // Update mission
        attributs = new ArrayList(Arrays.asList(Mission.getAttr()));
        attributs.remove(0);
        String[] valuesMission = {Integer.toString(mission.getYear()),
            sqlDateToString(mission.getStartDate()),
            sqlDateToString(mission.getEndDate()),
            mission.getReport_title(),
            mission.getComment(),
            mission.getMeetingInfo(),
            Boolean.toString(mission.isSoutenance()),
            mission.getKeyWord(),
            sqlSetId(EvalSheet.getTable(), mission.getEvalS().getId()),
            sqlSetId(VisitSheet.getTable(), mission.getVisitS().getId())};
        valuesArray = new ArrayList(Arrays.asList(valuesMission));
        query += updateInsert(Mission.getTable(), mission.getId(), attributs, valuesArray);

        // Update intern
        attributs = new ArrayList(Arrays.asList(Intern.getAttr()));
        attributs.remove(0);
        String[] values = {intern.getGroup(),
            intern.getFirst_name(),
            intern.getLast_name(),
            intern.getAddress(),
            intern.getSkills(),
            intern.getLinkedin(),
            sqlDateToString(intern.getBirthday()),
            sqlSetId(Mission.getTable(), intern.getMission().getId())};
        valuesArray = new ArrayList(Arrays.asList(values));
        query += updateInsert(Intern.getTable(), intern.getId(), attributs, valuesArray);

        // End Query
        query += "COMMIT;\n";
        //System.out.println(query);
        return query;
    }
}
