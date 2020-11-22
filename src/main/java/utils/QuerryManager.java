/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author NicoSoOl
 */
public class QuerryManager {

    public static String insert(String tableName, ArrayList<String> attributs, ArrayList<String> values) {
        String querry = "INSERT INTO 'ST2EEDB'.'" + tableName + "' (";
        for (int i = 0; i < attributs.size() - 1; i++) {
            querry += "'" + attributs.get(i) + "',";
        }
        if (0 < attributs.size()) {
            querry += "'" + attributs.get(attributs.size() - 1) + "'";
        }
        querry += ") VALUES (";
        for (int i = 0; i < values.size() - 1; i++) {
            querry += "'" + values.get(i) + "',";
        }
        if (0 < values.size()) {
            querry += "'" + values.get(values.size() - 1) + "'";
        }
        querry += ");";
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
}
