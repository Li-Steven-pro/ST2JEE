/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Intern;
import model.Teacher;
import static utils.CONSTANT.LIST_INTERNS_VIEW_PATH;
import utils.DataServices;

/**
 *
 * @author NicoSoOl
 */
public class internAddController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("Nouveau") != null) {
            HttpSession session = request.getSession();
            Teacher User = (Teacher) session.getAttribute("User");

            new DataServices(User.getUser(), User.getPwd()).modifQuery(
                    "START TRANSACTION;"
                    + "INSERT INTO `ST2EEDB`.`info_intern` () VALUES ();"
                    + "     SET @info_intern_id = LAST_INSERT_ID();"
                    + "INSERT INTO `ST2EEDB`.`mission` () VALUES ();"
                    + "     SET @mission_id = LAST_INSERT_ID();"
                    + "INSERT INTO `ST2EEDB`.`intern` (`teacher_id`, `info_intern_id`, `mission_id`) VALUES('" + User.getId() + "', @info_intern_id, @mission_id);"
                    + "COMMIT;");
            ArrayList<Intern> internsList = User.getAllInterns();
            session.setAttribute("internsList", internsList);
            request.setAttribute("internsList", internsList);
        }
        request.getRequestDispatcher(LIST_INTERNS_VIEW_PATH).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
