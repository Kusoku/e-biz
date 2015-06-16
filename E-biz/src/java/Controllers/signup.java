package Controllers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Konrad
 */
@WebServlet(name = "signup", urlPatterns = {"/signup"})
public class signup extends HttpServlet {

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
       
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmation = request.getParameter("password_confirmation");
        if(name == null || email == null || password == null || confirmation == null)
        {
            request.setAttribute("result", "Coś poszło nie tak");
            request.getRequestDispatcher("/signup.jsp").forward(request,response);
        }
        else if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmation.isEmpty())
        {
            request.setAttribute("result", "Uzupełnij wymagane pola");
            request.getRequestDispatcher("/signup.jsp").forward(request,response);
        }
        else if(!password.equals(confirmation))
        {
            request.setAttribute("result", "Niezgodne hasło");
            request.getRequestDispatcher("/signup.jsp").forward(request,response);
        }
        else
        {
            try {
                Common.DAO dao = new Common.DAO();
                if(dao.GetUser(email) != null) throw new SQLException("Email zajęty");
                
                dao.Register(email, name, password);
                
                request.setAttribute("result", "Witaj, "+name+".");

                Models.User user = dao.Login(email, password);
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                
                //request.getRequestDispatcher("/index.jsp").forward(request,response);
                response.sendRedirect("/E-biz/");
                
            } catch (SQLException ex) {
                request.setAttribute("result", "Nie udało się wykonać operacji.\n"+ex.getMessage());
                request.getRequestDispatcher("/signup.jsp").forward(request,response);
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
