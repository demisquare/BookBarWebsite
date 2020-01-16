package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@SuppressWarnings("serial")
public final class GestioneUtenti extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		// placeholder da eliminare
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User(0, "Utente UNO", "user"));
		users.add(new User(1, "Utente DUE", "user"));
		users.add(new User(2, "Utente TRE", "user"));
		users.add(new User(3, "Utente QUATTRO", "admin"));
		// ========== 
		
		req.setAttribute("users", users);
		req.getRequestDispatcher("GestioneUtenti.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String operation = req.getParameter("operation");
		int subject;
		String email;
		String password;
		boolean isAdmin = false;
		
		switch (operation) {
		case "del":
			subject = Integer.parseInt(req.getParameter("id"));
			// delete nel database
			System.out.println("Soggetto da eliminare: " + subject);
			break;
		case "add":
			email = req.getParameter("email");
			password = req.getParameter("password");
			isAdmin = Boolean.parseBoolean(req.getParameter("isAdmin"));
			// add nel database
			System.out.println("Soggetto da aggiungere: " + email + " password: " + password + " isAdmin: " + isAdmin);
			break;
		}

		res.setStatus(200);
	}
}