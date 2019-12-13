package it.unical.siw.bookbar;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public final class Test extends HttpServlet {
    public void doGet(HttpServletRequest req,
                      HttpServletResponse res)
      throws IOException, ServletException {
    	
        req.setAttribute("pippo",5);
        req.getRequestDispatcher("tmp.jsp").forward(req, res);	
    }
} 