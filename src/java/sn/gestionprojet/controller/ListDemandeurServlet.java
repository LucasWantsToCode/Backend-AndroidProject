/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sn.gestionprojet.dao.DemandeurImpl;
import sn.gestionprojet.dao.IDemandeur;
import sn.gestionprojet.entities.Cv;
import sn.gestionprojet.entities.Demandeur;

/**
 *
 * @author darkshadow
 */
@WebServlet(name = "ListDemandeurServlet", urlPatterns = {"/ListDemandeur"})
public class ListDemandeurServlet extends HttpServlet {
    List<Demandeur> ld = null;
    IDemandeur dm_dao = new DemandeurImpl();
    

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
            out.println("<title>Servlet ListDemandeurServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListDemandeurServlet at " + request.getContextPath() + "</h1>");
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
        
        ld = dm_dao.findAll();
        
        if(ld != null){
            result = "OK";
            
        }else{
            result = "NOK";
        }
        JSONArray jsonArray = new JSONArray();
            for(Demandeur d: ld){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("response", result);
                jsonObject.put("id", d.getId());
                jsonObject.put("nom", d.getNom());
                jsonObject.put("prenom", d.getPrenom());
                jsonObject.put("email", d.getEmail());
                jsonObject.put("password", d.getPassword());

                jsonArray.add(jsonObject);
            }

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
