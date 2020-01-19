package controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/gestione_menu")
@SuppressWarnings("serial")

public final class GestioneMenu extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setAttribute("menu", "Esempio");
		req.getRequestDispatcher("GestioneMenu.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setStatus(200);
	}
}