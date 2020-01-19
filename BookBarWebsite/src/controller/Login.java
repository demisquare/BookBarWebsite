package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.DBManager;

@SuppressWarnings("serial")
public class Login extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object utente = req.getSession().getAttribute("utente");
		String isLogout = req.getParameter("logout");
		if (isLogout != null && isLogout.equals("true")) {
			req.getSession().removeAttribute("utente");
			RequestDispatcher rd = req.getRequestDispatcher("");
			rd.forward(req, resp);
		}else {
			if (utente == null) {
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.forward(req, resp);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Utente utente = DBManager.getInstance().findUserByCredentials(email, password);
		if (utente != null) {
			req.getSession().setAttribute("utente", utente);
//			resp.sendRedirect(".");
			
			RequestDispatcher rd = req.getRequestDispatcher("AreaClienti.jsp");
			rd.forward(req, resp);
		}else {
			//req.getSession().invalidate();
			RequestDispatcher rd = req.getRequestDispatcher("error.html");
			rd.forward(req, resp);
		}
		
	}
}









