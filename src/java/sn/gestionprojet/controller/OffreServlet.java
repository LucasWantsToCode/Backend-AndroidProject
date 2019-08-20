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
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sn.gestionprojet.dao.EntrepriseImpl;
import sn.gestionprojet.dao.IEntreprise;
import sn.gestionprojet.dao.IOffre;
import sn.gestionprojet.dao.OffreImpl;
import sn.gestionprojet.entities.Demandeur;
import sn.gestionprojet.entities.Entreprise;
import sn.gestionprojet.entities.Offre;
import sn.gestionprojet.entities.Domaine;


/**
 *
 * @author darkshadow
 */
@WebServlet(name = "Offres", urlPatterns = {"/OffreServlet"})
public class OffreServlet extends HttpServlet {

    Entreprise ent = null;
    IEntreprise ent_dao = new EntrepriseImpl();
    IOffre offre_dao = new OffreImpl();
    int ok =0;
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
            out.println("<title>Servlet OffreServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OffreServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session  = request.getSession();
        // ent = (Entreprise)session.getAttribute("ent");
         String result = null;
        // if(ent != null){
             
            if(request.getParameter("entmail") != null && request.getParameter("dateO") != null && request.getParameter("libelle") != null && request.getParameter("dom_id") != null){
                String usermail = request.getParameter("entmail").toString();
                String dateO = request.getParameter("dateO").toString();
                String libelle = request.getParameter("libelle").toString();
                int dom_id = Integer.parseInt(request.getParameter("dom_id").toString());
                
                Offre offre = new Offre();
                offre.setDateO(dateO);
                offre.setLibelle(libelle);
                
                ent = ent_dao.get(usermail);
                offre.setEntreprise(ent);
                Domaine dom = new Domaine();
                dom.setId(dom_id);
                
                offre.setDomaine(dom);
                ok =  offre_dao.persiste(offre);
                if(ok == 0){
                    result = "NOK";
                }
                else{
                    
                    result = "OK";
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
         /*}else{
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
         }*/
        
        
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
