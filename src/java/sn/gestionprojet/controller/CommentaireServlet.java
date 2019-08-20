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
import sn.gestionprojet.dao.CommentaireImpl;
import sn.gestionprojet.dao.DemandeurImpl;
import sn.gestionprojet.dao.ICommentaire;
import sn.gestionprojet.dao.IDemandeur;
import sn.gestionprojet.entities.Commentaire;
import sn.gestionprojet.entities.Demandeur;
import sn.gestionprojet.entities.Favorite;
import sn.gestionprojet.entities.Offre;

/**
 *
 * @author darkshadow
 */
@WebServlet(name = "Commentaires", urlPatterns = {"/Commentaire"})
public class CommentaireServlet extends HttpServlet {

    Demandeur dem = null;
    IDemandeur dem_dao = new DemandeurImpl();
    ICommentaire coms = new CommentaireImpl();
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
            out.println("<title>Servlet CommentaireServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentaireServlet at " + request.getContextPath() + "</h1>");
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
      
            if(request.getParameter("usermail")!= null && request.getParameter("off_id")!= null && request.getParameter("libelle") != null){
                String usermail = request.getParameter("usermail");
                int off_id = Integer.parseInt(request.getParameter("off_id"));
                String libelle = request.getParameter("libelle");
                
                Commentaire com = new Commentaire();
                com.setLibelle(libelle);
                //Offer management 
                Offre off = new Offre();
                off.setId(off_id);
                com.setId_off(off);
             
                //Demandeur management
                dem = dem_dao.get(usermail);
                
                com.setId_dem(dem);
                ok = coms.persist(com);
                
                if(ok == 0){
                    result = "NOK";
                }else{
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
