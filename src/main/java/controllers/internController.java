package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EvalSheet;
import model.Intern;
import model.Mission;
import model.VisitSheet;
import static utils.CONSTANT.INTERN_VIEW_PATH;
import static utils.CONSTANT.LIST_INTERNS_VIEW_PATH;

/**
 *
 * @author steve
 */
public class internController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet internController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet internController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
//        Intern intern = loadIntern(request);
//        request.setAttribute("intern", intern);
//        request.getRequestDispatcher(INTERN_VIEW_PATH).forward(request, response);
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

    public Intern loadIntern(HttpServletRequest request) {

        Intern in = new Intern();
        in.setGroup(request.getParameter("GroupStudent"));
        in.setLast_name(request.getParameter("LastNameStudent"));
//        in.setFirst_name(request.getParameter("FirstNameStudent"));
        in.setId(Integer.parseInt(request.getParameter("id_student")));
        in.setAddress(request.getParameter("Adresse"));
//        in.setSkills(request.getParameter("Skills"));
//        in.setLinkedin(request.getParameter("Linkedin"));
//        in.setBirthday(stringToSqlDate(request.getParameter("Birthday"),"yyyy-mm-dd"));
        Mission mi = new Mission();
//        mi.setId(Integer.parseInt(request.getParameter("id_mission")));
//        mi.setYear(Integer.parseInt(request.getParameter("Year")));
        mi.setStartDate(stringToSqlDate(request.getParameter("Debut"), "yyyy-mm-dd"));
        mi.setEndDate(stringToSqlDate(request.getParameter("Fin"), "yyyy-mm-dd"));
//        mi.setReport_title(request.getParameter("Report_title"));
//        mi.setComment(request.getParameter("CommentMission"));
//        mi.setMeetingInfo(request.getParameter("MettingInfo"));
//        mi.setSoutenance(Boolean.parseBoolean(request.getParameter("Soutenance")));

        EvalSheet es = new EvalSheet();
//        es.setId(Integer.parseInt(request.getParameter("id_evalS")));
//        es.setComment(request.getParameter("CommentEvalSheet"));
        es.setGradeTech(Integer.parseInt(request.getParameter("NoteTech")));
        es.setGradeCom(Integer.parseInt(request.getParameter("NoteCom")));
//        es.setDone(Boolean.parseBoolean(request.getParameter("DoneEval")));

        VisitSheet vs = new VisitSheet();
//        vs.setId(Integer.parseInt(request.getParameter("id_visitS")));
//        vs.setPlanned(Boolean.parseBoolean(request.getParameter("PlannedVisit")));
//        vs.setDone(Boolean.parseBoolean(request.getParameter("DoneVisit")));

        mi.setEvalS(es);
        mi.setVisitS(vs);
        in.setMission(mi);
        return in;
    }

    public java.sql.Date stringToSqlDate(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return new java.sql.Date(dateFormat.parse(date).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(internController.class.getName()).log(Level.SEVERE, null, ex);
        };
        return null;
    }
}
