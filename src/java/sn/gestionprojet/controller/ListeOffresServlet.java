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
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sn.gestionprojet.dao.DemandeurImpl;
import sn.gestionprojet.dao.IDemandeur;
import sn.gestionprojet.dao.IOffre;
import sn.gestionprojet.dao.OffreImpl;
import sn.gestionprojet.entities.Demandeur;
import sn.gestionprojet.entities.Entreprise;
import sn.gestionprojet.entities.Offre;

/**
 *
 * @author darkshadow
 */
@WebServlet(name = "ListeOffresServlet", urlPatterns = {"/ListeOffres"})
public class ListeOffresServlet extends HttpServlet {

    Demandeur dem = null;
    IDemandeur dem_dao = new DemandeurImpl();
    Entreprise ent = null;
    IOffre offre_dao = new OffreImpl();
    List<Offre> list_o = null;
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
            out.println("<title>Servlet ListeOffresServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListeOffresServlet at " + request.getContextPath() + "</h1>");
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
       // HttpSession session  = request.getSession();
         //dem = (Demandeur)session.getAttribute("user");
         //ent = (Entreprise)session.getAttribute("ent");
         
       // if(dem != null){
       if(request.getParameter("action").equals("listoffredem") && request.getParameter("usermail") != null){
           
           String usermail = request.getParameter("usermail");
           
           dem = dem_dao.get(usermail);
           
           if(dem != null){
                result = "OK";
                list_o = offre_dao.domdemOffre(dem);
               
                if(list_o != null){
                   JSONArray jsonArray = new JSONArray();
            
                    for(Offre o: list_o){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("response", result);
                       jsonObject.put("id", o.getId());
                        jsonObject.put("dateO", o.getDateO());
                        jsonObject.put("libelle", o.getLibelle());
                        jsonObject.put("entreprise", o.getEntreprise().getId());
                        jsonObject.put("domaine", o.getDomaine().getId());

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
            }     
        }else if(request.getParameter("action").equals("searchoffre") && request.getParameter("motcle") != null){
            
            String motcle = request.getParameter("motcle");
            list_o = offre_dao.getOffre(motcle);
            if(list_o != null){
                result = "OK";
                JSONArray jsonArray = new JSONArray();

                for(Offre o: list_o){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("response", result);
                   jsonObject.put("id", o.getId());
                    jsonObject.put("dateO", o.getDateO());
                    jsonObject.put("libelle", o.getLibelle());
                    jsonObject.put("entreprise", o.getEntreprise().getId());
                    jsonObject.put("domaine", o.getDomaine().getId());

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
        }
       
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
