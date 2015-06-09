package Controllers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.User;

/**
 *
 * @author Konrad
 */
@WebServlet(name = "signin", urlPatterns = {"/signin"})
public class signin extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            Models.User user;

            String email = request.getParameter("session_email");
            String password = request.getParameter("session_password");

            if(email == null || password == null)
                throw new Exception();
            //if(email.isEmpty() || password.isEmpty())
            //    throw new Exception("Uzupełnij wymagane pola.");


            Common.DAO dao = new Common.DAO();
            user = dao.Login(email, password);

            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            request.setAttribute("result", "Witaj, "+user.getUsername()+".");

            request.getRequestDispatcher("/index.jsp").forward(request,response);
            
            
        } catch (Exception ex){

            if(ex.getMessage() != null)
            {
                request.setAttribute("result", "Nie udało się wykonać operacji.\n"+ex.getMessage());
            }
            request.getRequestDispatcher("/signin.jsp").forward(request,response);
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
