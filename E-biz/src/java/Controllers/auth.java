/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Common.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Konrad
 */
@WebServlet(name = "auth", urlPatterns = {"/auth"})
public class auth extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
        
        Models.User user = Helper.auth_admin(request, response);
        String id_ = request.getParameter("id");
        int id = Integer.parseInt(id_);
        String option = request.getParameter("option");
        
        Common.DAO dao = new Common.DAO();
        
        if(user == null)
        {
            return;
        }     
        else if(id_ == null || option == null)
        {
            request.getRequestDispatcher("/").forward(request,response);
        }
        else
        {
            try 
            {
                if(option.equals("0"))
                    dao.AuthUser(true, true, id);
                if(option.equals("1") || option.equals("2"))
                    dao.AuthUser(false, true, id);
                if(option.equals("3"))
                    dao.AuthUser(false, false, id);
                request.setAttribute("result", "zmieniono.");
                response.sendRedirect("/ProjektXML/edit_user?id="+id_);
                //request.getRequestDispatcher("/").forward(request,response);
            } catch (Exception ex) {
                request.setAttribute("result", "Nie udało się wykonać operacji.\n"+ex.getMessage());
                request.getRequestDispatcher("/").forward(request,response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
