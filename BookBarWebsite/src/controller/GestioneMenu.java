package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public final class GestioneMenu extends HttpServlet {
    public void doGet(HttpServletRequest req,
                      HttpServletResponse res)
      throws IOException, ServletException {
    	
    	
        req.setAttribute("menu", "Esempio");
        req.getRequestDispatcher("GestioneMenu.jsp").forward(req, res);	
    }
} 