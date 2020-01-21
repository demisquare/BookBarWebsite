package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.DBManager;

@SuppressWarnings("serial")
public final class GestioneUtenti extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		DBManager dbm = persistence.DBManager.getInstance();
		List<Utente> utenti = new LinkedList<>();
		utenti = dbm.getAllUsers();

		req.setAttribute("utentiPresenti", utenti.size());
		req.setAttribute("users", utenti);
		req.getRequestDispatcher("GestioneUtenti.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		DBManager dbm = persistence.DBManager.getInstance();
		String operation = req.getParameter("operation");
		int subject;
		String email;
		String firstname;
		String lastname;
		String password;
		boolean isAdmin = false;

		switch (operation) {
		case "del":
			subject = Integer.parseInt(req.getParameter("id"));
			// delete nel database
			Utente user = dbm.findUserByPrimaryKey(subject);
			dbm.deleteUser(user);
			System.out.println("Soggetto da eliminare: " + subject);
			res.setStatus(200);
			break;

		case "add":
			firstname = req.getParameter("fn");
			lastname = req.getParameter("ln");
			email = req.getParameter("email");

			password = req.getParameter("password");
			isAdmin = Boolean.parseBoolean(req.getParameter("isAdmin"));
			String role = "user";
			if (isAdmin)
				role = "admin";
			System.out.println("Soggetto da aggiungere | email " + email + " password " + password + " role: " + isAdmin
					+ " firstname " + firstname + " lastname " + lastname);
			Utente newUser = new Utente(firstname, lastname, email, role);
			newUser.setPassword(password);
			dbm.addUser(newUser);
			res.setStatus(200);
			// add nel database
			break;

		default:
			res.setStatus(200);
			break;
		}

	}

}