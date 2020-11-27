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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Teacher;
import static utils.CONSTANT.LIST_INTERNS_VIEW_PATH;
import utils.DataServices;

/**
 *
 * @author steve
 */
public class auth {
    
    /**
    * Check if the user is connect 
    * otherwise having User in session scope
    * redirect to the login page if not 
    *@param request servlet request
    *@param response servlet response
    **/
    public static void isConnected(HttpServletRequest request,HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("User") == null){
            try {
                session.invalidate();
                response.sendRedirect("login");
            } catch (IOException ex) {
                Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
    * Check if the user is associated with the user 
    * redirect to the list of interns page if not 
    * 
    *@param request servlet request
    *@param response servlet response
    **/
    public static void hasAccessIntern(HttpServletRequest request, HttpServletResponse response,int id){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("User");
        DataServices dbs = new DataServices(teacher.getUser(), teacher.getPwd());
        ResultSet rs = dbs.selectQuery("SELECT * from intern WHERE teacher_id = '" + teacher.getId() + "' AND info_intern_id = '" + id + "';");
        try {
            if (rs.next() == true){}
            else{
                request.setAttribute("internsList", session.getAttribute("internsList"));
                request.getRequestDispatcher(LIST_INTERNS_VIEW_PATH).forward(request, response);
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
