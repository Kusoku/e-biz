/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kwydin
 */
@WebServlet(name = "choose", urlPatterns = {"/choose"})
public class choose extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        
        Models.Aquarium aquarium = (Models.Aquarium) request.getSession().getAttribute("aquarium");
        String table = request.getSession().getAttribute("table").toString();
        
        if(aquarium==null){
            aquarium = new Models.Aquarium();
        }
        
        Common.DAO dao = new Common.DAO();
        
        if(table=="tank"){
            try {
                aquarium.setTank(dao.GetTank(Integer.parseInt(request.getParameter("id"))));
            } catch (SQLException ex) {
                Logger.getLogger(choose.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("table", "filter");
        }
        if(table=="filter"){
            try {
                aquarium.setFilter(dao.GetFilter(Integer.parseInt(request.getParameter("id"))));
            } catch (SQLException ex) {
                Logger.getLogger(choose.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("table", "heater");
        }
        
        if(table=="heater"){
            try {
                aquarium.setHeater(dao.GetHeater(Integer.parseInt(request.getParameter("id"))));
            } catch (SQLException ex) {
                Logger.getLogger(choose.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("table", "aq");
        }
        
        
        
        request.getSession().setAttribute("aquarium", aquarium);
        request.getRequestDispatcher("/build.jsp").forward(request,response);
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
