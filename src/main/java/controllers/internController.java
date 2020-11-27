package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import middleware.auth;
import model.EvalSheet;
import model.Intern;
import model.Mission;
import model.Teacher;
import model.VisitSheet;
import static utils.CONSTANT.*;
import utils.DataServices;
import utils.QuerryManager;

/**
 *
 * @author steve
 */
public class internController extends HttpServlet {

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
        auth.isConnected(request, response);
        HttpSession session = request.getSession();
        List<Intern> internList = (ArrayList<Intern>) session.getAttribute("internsList");
        if (internList == null) {
            Teacher teacher = (Teacher) session.getAttribute("User");
            internList = teacher.getAllInterns();
            session.setAttribute("internsList", internList);
        }
        request.setAttribute("internsList", internList);
        request.getRequestDispatcher(LIST_INTERNS_VIEW_PATH).forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            auth.isConnected(request, response);
        //        Intern intern = loadIntern(request);
        //        request.setAttribute("intern", intern);
        //        request.getRequestDispatcher(INTERN_VIEW_PATH).forward(request, response);
            if (request.getParameter("UpdateAll") != null) {
                System.out.println("Controller Update all interns");
                System.out.println(request.getParameter("id_student"));
                System.out.println(request.getPathInfo());
                HttpSession session = null;
                try {
                    session = request.getSession();
                }catch(java.lang.IllegalStateException ex){
                    response.sendRedirect("login");
                }
                ArrayList<Intern> internsList = (ArrayList<Intern>) session.getAttribute("internsList");
                UpdateInternsList(internsList, request);

                Teacher User = (Teacher) session.getAttribute("User");
                DataServices ds = new DataServices(User.getUser(), User.getPwd());
                internsList.forEach(intern -> {ds.modifQuery(QuerryManager.updateIntern(intern));});
                //internsList = User.getAllInterns();
                session.setAttribute("internsList", internsList);
                request.setAttribute("internsList", internsList);
            }
            //response.sendRedirect(request.getContextPath()+ "/intern");
            request.getRequestDispatcher(LIST_INTERNS_VIEW_PATH).forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(internController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(internController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /* unused
    public Intern loadIntern(HttpServletRequest request, int id) {

        Intern in = new Intern();
        in.setGroup(request.getParameter("GroupStudent" + id));
        in.setLast_name(request.getParameter("LastNameStudent" + id));
//        in.setFirst_name(request.getParameter("FirstNameStudent"));
        in.setId(id);
        in.setAddress(request.getParameter("Adresse" + id));
//        in.setSkills(request.getParameter("Skills"));
//        in.setLinkedin(request.getParameter("Linkedin"));
//        in.setBirthday(stringToSqlDate(request.getParameter("Birthday"),"yyyy-mm-dd"));
        Mission mi = new Mission();
//        mi.setId(Integer.parseInt(request.getParameter("id_mission")));
//        mi.setYear(Integer.parseInt(request.getParameter("Year")));
        mi.setStartDate(stringToSqlDate(request.getParameter("Debut" + id), "yyyy-mm-dd"));
        mi.setEndDate(stringToSqlDate(request.getParameter("Fin" + id), "yyyy-mm-dd"));
//        mi.setReport_title(request.getParameter("Report_title"));
//        mi.setComment(request.getParameter("CommentMission"));
//        mi.setMeetingInfo(request.getParameter("MettingInfo"));
//        mi.setSoutenance(Boolean.parseBoolean(request.getParameter("soutenance")));

        EvalSheet es = new EvalSheet();
//        es.setId(Integer.parseInt(request.getParameter("id_evalS")));
//        es.setComment(request.getParameter("CommentEvalSheet"));
        es.setGradeTech(Integer.parseInt(request.getParameter("NoteTech" + id)));
        es.setGradeCom(Integer.parseInt(request.getParameter("NoteCom" + id)));
//        es.setDone(Boolean.parseBoolean(request.getParameter("DoneEval")));

        VisitSheet vs = new VisitSheet();
//        vs.setId(Integer.parseInt(request.getParameter("id_visitS")));
//        vs.setPlanned(Boolean.parseBoolean(request.getParameter("plannif$")));
//        vs.setDone(Boolean.parseBoolean(request.getParameter("faite")));

        mi.setEvalS(es);
        mi.setVisitS(vs);
        in.setMission(mi);
        return in;
    }*/

    public java.sql.Date stringToSqlDate(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return new java.sql.Date(dateFormat.parse(date).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(internController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unformattable date");
        }
        return null;
    }

     /**
     * Looping the insertion of form data in insterns
     *
     * @param internsList list of interns
     * @param request servlet request
     */
    private void UpdateInternsList(ArrayList<Intern> internsList, HttpServletRequest request) {
        internsList.forEach(intern -> {UpdateInternFromRequest(intern, request);});
    }
    
    /**
     * Insert data from form into instern
     *
     * @param intern intern to be update
     * @param request servlet request
     */
    private void UpdateInternFromRequest(Intern intern, HttpServletRequest request) {
        intern.ShowConsole();
        intern.setGroup(request.getParameter("GroupStudent" + intern.getId()));
        intern.setLast_name(request.getParameter("LastNameStudent" + intern.getId()));
//      in.setFirst_name(request.getParameter("FirstNameStudent
        intern.setAddress(request.getParameter("Adresse" + intern.getId()));
//        in.setSkills(request.getParameter("Skills"));
//        in.setLinkedin(request.getParameter("Linkedin"));
//        in.setBirthday(stringToSqlDate(request.getParameter("Birthday"),"yyyy-mm-dd"));
        Mission mi = intern.getMission();
//        mi.setId(Integer.parseInt(request.getParameter("id_mission")));
//        mi.setYear(Integer.parseInt(request.getParameter("Year")));
        mi.setStartDate(stringToSqlDate(request.getParameter("Debut" + intern.getId()), "yyyy-mm-dd"));
        mi.setEndDate(stringToSqlDate(request.getParameter("Fin" + intern.getId()), "yyyy-mm-dd"));
//        mi.setReport_title(request.getParameter("Report_title"));
//        mi.setComment(request.getParameter("CommentMission"));
//        mi.setMeetingInfo(request.getParameter("MettingInfo"));
        mi.setSoutenance(null != request.getParameter("soutenance" + intern.getId()));
        System.out.println("mi soutenance:" + mi.isSoutenance() + "request " + request.getParameter("soutenance" + intern.getId()));
        EvalSheet es = mi.getEvalS();
        System.out.println("es :" + es);
//        es.setId(Integer.parseInt(request.getParameter("id_evalS")));
//        es.setComment(request.getParameter("CommentEvalSheet"));
        es.setGradeTech(Integer.parseInt(request.getParameter("NoteTech" + intern.getId())));
        es.setGradeCom(Integer.parseInt(request.getParameter("NoteCom" + intern.getId())));
//        es.setDone(Boolean.parseBoolean(request.getParameter("DoneEval")));
        VisitSheet vs = mi.getVisitS();
        System.out.println("vs :" + vs);
//        vs.setId(Integer.parseInt(request.getParameter("id_visitS")));
        vs.setPlanned(null != request.getParameter("plannif" + intern.getId()));
        vs.setDone(null != request.getParameter("faite" + intern.getId()));
        //intern.ShowConsole();
        System.out.println("vs planned :" + vs.isPlanned());
        System.out.println("vs done:" + vs.isDone());
    }
}
