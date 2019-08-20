/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sn.gestionprojet.dao.CvImple;
import sn.gestionprojet.dao.DemandeurImpl;
import sn.gestionprojet.dao.ICv;
import sn.gestionprojet.dao.IDemandeur;
import sn.gestionprojet.entities.Cv;
import sn.gestionprojet.entities.Demandeur;
import sn.gestionprojet.entities.Domaine;

/**
 *
 * @author darkshadow
 */
@WebServlet(name = "CvServlet", urlPatterns = {"/Cv"})
public class CvServlet extends HttpServlet {

    Demandeur dem = null;
    Cv cvs = null;
    ICv cv_dao = new CvImple();
    IDemandeur dems = new DemandeurImpl();
    int ok = 0;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CvServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CvServlet at " + request.getContextPath() + "</h1>");
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
        String result = null;
        
     
        if(request.getParameter("action").equals("save")  &&  request.getParameter("usermail") != null && request.getParameter("formation") != null &&
                request.getParameter("competences") != null && request.getParameter("dom_id") != null && request.getParameter("passion") != null){
            //result = dem.getNom();
           
            String usermail = request.getParameter("usermail").toString();
            String formation = request.getParameter("formation").toString();
            String competences = request.getParameter("competences").toString();

            int dom_id = Integer.parseInt(request.getParameter("dom_id"));
            String passion = request.getParameter("passion").toString();

            Cv cv = new Cv();
            cv.setFormation(formation);
            cv.setCompetence(competences);
            
            dem = dems.get(usermail);
            cv.setDemandeur(dem);

            //Domaine management
            Domaine domaine = new Domaine();
            domaine.setId(dom_id);
            cv.setDomaine(domaine);
            cv.setPassion(passion);

            ok = cv_dao.persist(cv);

            if(ok == 0){
                result = "NOK";
            }else{
                result = "OK";
            }
        }else if(request.getParameter("action").equals("update") && request.getParameter("usermail") != null /* && request.getParameter("formation") != null && request.getParameter("competences") != null && request.getParameter("dom_id") != null && request.getParameter("passion") != null*/){
            
            String usermail = request.getParameter("usermail").toString();
            dem = dems.get(usermail);
            if(request.getParameter("formation") == null && request.getParameter("competences") == null && request.getParameter("dom_id") == null && request.getParameter("passion") == null ){
                
                cvs = cv_dao.get(dem);
                cvs.setDemandeur(dem);
                ok = cv_dao.update(cvs);
                
                if(ok == 0){
                    result = "NOK";
                }else{
                    result = "OK";
                }
            }else{
                
                String formation = request.getParameter("formation").toString();
                String competences = request.getParameter("competences").toString();
                int dom_id = Integer.parseInt(request.getParameter("dom_id"));
                String passion = request.getParameter("passion").toString();
                cvs = cv_dao.get(dem);
                cvs.setFormation(formation);
                cvs.setCompetence(competences);

                
                cvs.setDemandeur(dem);


                //Domaine management
                Domaine domaine = new Domaine();
                domaine.setId(dom_id);
                cvs.setDomaine(domaine);
                cvs.setPassion(passion);

                //result = ""+cvs.getId();
                ok = cv_dao.update(cvs);
                
                if(ok == 0){
                    result = "NOK";
                }else{
                    result = "OK";
                }
                
            }        
        }
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("response", result);
        
        
        jsonArray.add(jsonObject);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        
        StringWriter out = new StringWriter();
        jsonArray.writeJSONString(out);
        String jsonText = out.toString();
        
        response.getWriter().print(jsonText);
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
