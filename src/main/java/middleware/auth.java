/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Teacher;
import utils.DataServices;

/**
 *
 * @author steve
 */
public class auth {
    
    private static boolean isConnected(HttpServletRequest request,HttpSession session){
        if (session.getAttribute("User")!= null){
            return true;
        }else{
            return false;
        }
    }
    private static boolean hasAccess(HttpServletRequest request,HttpSession session){
        if (auth.isConnected(request,session) == true){
            Teacher teacher = (Teacher) session.getAttribute("User");
            DataServices dbs = new DataServices(teacher.getUser(), teacher.getPwd());
//            ResultSet rs = dbs.selectQuery("SELECT * from teacher WHERE login = '" + user + "' AND password = '" + pwd + "';");
            if (dbs.getConnection() != null) {
                return true;
            }
            else{
                return false;
            }
        }else{
            return false; 
        }
    }
    private static boolean hasAccessIntern(HttpServletRequest request, int id){
        HttpSession session = request.getSession();
        if (auth.hasAccess(request, session) == true){
            Teacher teacher = (Teacher) session.getAttribute("User");
            DataServices dbs = new DataServices(teacher.getUser(), teacher.getPwd());
            ResultSet rs = dbs.selectQuery("SELECT * from intern WHERE teacher_id = '" + teacher.getId() + "' AND info_intern_id = '" + id + "';");
            try {
                if (rs.next()== true){
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public static void accessController(HttpServletRequest request,HttpServletResponse response) 
            throws IOException{
        HttpSession session = request.getSession();
        if(auth.hasAccess(request, session) == false){
            session.invalidate();
            response.sendRedirect("login");
        }
    }
    
    public static void accessControllerForIntern(HttpServletRequest request,HttpServletResponse response, int id)
            throws IOException{
        HttpSession session = request.getSession();
        if(auth.hasAccessIntern(request,id) == false){
            session.invalidate();
            response.sendRedirect("login");
        }
    }
}
