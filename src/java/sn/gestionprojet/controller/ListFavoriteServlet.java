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
import sn.gestionprojet.dao.FavoriteImpl;
import sn.gestionprojet.dao.IDemandeur;
import sn.gestionprojet.dao.IFavorite;
import sn.gestionprojet.entities.Demandeur;
import sn.gestionprojet.entities.Favorite;


/**
 *
 * @author darkshadow
 */
@WebServlet(name = "ListFavoriteServlet", urlPatterns = {"/ListFavorite"})
public class ListFavoriteServlet extends HttpServlet {

    Demandeur dem = null;
    IDemandeur dem_dao = new DemandeurImpl();
    List<Favorite> favs = null;
    IFavorite fav_dao = new FavoriteImpl();
    
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
            out.println("<title>Servlet ListFavoriteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListFavoriteServlet at " + request.getContextPath() + "</h1>");
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
        if(request.getParameter("usermail")!= null){
            String usermail = request.getParameter("usermail");
            
            dem = dem_dao.get(usermail);
            
            result = "OK";
            
            JSONArray jsonArray = new JSONArray();
            favs = fav_dao.favorite(dem);
            
            for(Favorite fa: favs){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("res", result);
                jsonObject.put("id", fa.getId());
                jsonObject.put("demandeur", fa.getDemandeur().getId());
                jsonObject.put("offre", fa.getOffre().getId());

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
            /*else{
            result = "NOK";
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
    //}

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
