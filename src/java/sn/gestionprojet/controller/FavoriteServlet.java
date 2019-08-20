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
import sn.gestionprojet.dao.DemandeurImpl;
import sn.gestionprojet.dao.FavoriteImpl;
import sn.gestionprojet.dao.IDemandeur;
import sn.gestionprojet.dao.IFavorite;
import sn.gestionprojet.entities.Favorite;
import sn.gestionprojet.entities.Demandeur;
import sn.gestionprojet.entities.Offre;

/**
 *
 * @author darkshadow
 */
@WebServlet(name = "FavoriteServlet", urlPatterns = {"/Favorite"})
public class FavoriteServlet extends HttpServlet {
    
    IFavorite favs = new FavoriteImpl();
    Demandeur dem = null;
    IDemandeur dem_dao = new DemandeurImpl();
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
            out.println("<title>Servlet FavoriteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FavoriteServlet at " + request.getContextPath() + "</h1>");
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
        
        if(request.getParameter("action").equals("store") && request.getParameter("usermail")!= null && request.getParameter("off_id")!= null){
            String usermail = request.getParameter("usermail");
            int off_id = Integer.parseInt(request.getParameter("off_id"));
            Offre off = new Offre();
            off.setId(off_id);

            Favorite fav = new Favorite();
            dem = dem_dao.get(usermail);
            fav.setDemandeur(dem);
            fav.setOffre(off);
            ok = favs.persist(fav);

            if(ok == 0){
                result = "NOK";
            }else{
                result = "OK";
            }
        }
           /* }else if(request.getParameter("action") == "update"){
                if(request.getParameter("off_id")!= null){
                int off_id = Integer.parseInt(request.getParameter("off_id"));
                Offre off = new Offre();
                off.setId(off_id);
                
                Favorite fav = new Favorite();
                fav.setDemandeur(dem);
                fav.setOffre(off);
                ok = favs.persist(fav);
                
                if(ok == 0){
                    result = "NOK";
                }else{
                    result = "OK";
                }
            }
            }
        }else{
            result = "NOK";*/
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
