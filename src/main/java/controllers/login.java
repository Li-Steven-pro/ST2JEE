package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Teacher;
import static utils.CONSTANT.*;
/**
 *
 * @author steve
 */
public class login extends HttpServlet {

    private Teacher User;
    private String DeniedMsg = "Access Denied !";

    /**
     * Handles the HTTP <code>GET</code> method.
     * Redirect to the login page
     * 
     * @param request servlet request
     * @param response servlet
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Redirect to the login page
        request.getRequestDispatcher(LOGIN_VIEW_PATH).forward(request, response);
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
        // Load teacher in User using request scope
        setUserFromRequest(request);
        // Check if the user has db access 
        if (User.access()) {
            HttpSession session = request.getSession();
            //Put teacher info in session scope 
            session.setAttribute("User", User);
            //Put the list of interns in the session scope
            session.setAttribute("internsList", User.getAllInterns());
            //Put the list of interns in the request scope 
            request.setAttribute("internsList", session.getAttribute("internsList"));
            //Redirect to "WEB-INF/list.jsp" 
            request.getRequestDispatcher(LIST_INTERNS_VIEW_PATH).forward(request, response);
        } else {
            // Redirect to "WEB-INF/login.jsp" if he doesn't have access
            request.setAttribute("errMsg", DeniedMsg);
            request.getRequestDispatcher(LOGIN_VIEW_PATH).forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for login";
    }

    /**
     * Set the request information in the user as teacher object
     * 
     * @param request servlet request
     */
    private void setUserFromRequest(HttpServletRequest request) {
        User = new Teacher();
        User.setUser(request.getParameter("loginForm"));
        User.setPwd(request.getParameter("pwdForm"));
    }
}
