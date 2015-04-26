/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Konrad
 */
public class Helper {
    public static Models.User auth_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Object o = request.getSession(false).getAttribute("user");
        if(o == null)
        {
            request.setAttribute("result", "Niezalogowany.");
            request.getRequestDispatcher("/").forward(request,response);
            return null;
        }
        Models.User user = (Models.User)o;
        return user;
    }
    
    public static Models.User auth_admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Object o = request.getSession(false).getAttribute("user");
        if(o == null)
        {
            request.setAttribute("result", "Niezalogowany.");
            request.getRequestDispatcher("/").forward(request,response);
            return null;
        }
        Models.User user = (Models.User)o;
        if(user.isAdmin())
            return user;
        else
        {
            request.setAttribute("result", "Niezalogowany.");
            request.getRequestDispatcher("/").forward(request,response);
            return null;
        }
    }
    
    public static Models.User auth_self(Models.User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Common.DAO dao = new DAO();
        Models.User u = null;
        try{
            u = dao.GetUser(Integer.parseInt(request.getParameter("id")));
            if(u == null)
                throw new Exception("Nie znaleziono użytkownika");
            if(!user.isAdmin() && u.getId() != user.getId())
                throw new Exception("Nie możesz edytować cudzego profilu");
        }
        catch(Exception ex)
        {
            request.setAttribute("result", "Błąd: "+ex.getMessage());
            request.getRequestDispatcher("/").forward(request,response);
        }
        return u;        
    }
    
    public static String show_results(HttpServletRequest request)
    {
        String info = (String)request.getAttribute("result");
        if(info == null) return "";
        return "<div class=\"result\">"+info+"</div>";
    }
    
}
