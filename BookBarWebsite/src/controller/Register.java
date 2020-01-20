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
public class Register extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBManager dbm = persistence.DBManager.getInstance();
		
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirm_password = req.getParameter("confirmpassword");
		
		String role = "user";
		
		System.out.println("Soggetto da aggiungere | email " + email + " password " + password + " role: " + role
				+ " firstname " + firstname + " lastname " + lastname + " password_confirm " + confirm_password);
		// verifica la conferma delle password...
		if (password.equals(confirm_password)) {
			// verifica se l'utente esiste già...
			Utente utente = dbm.findUserByCredentials(email, password);
			if (utente == null) {
				
				// inserisce l'utente nel database...
				utente = new Utente(firstname, lastname, email, role);
				utente.setPassword(password);
				dbm.addUser(utente);
				System.out.println("aggiunto con successo");
					
				// login automatico...
				req.getSession().setAttribute("utente", utente);
				RequestDispatcher rd = req.getRequestDispatcher("AreaClienti.jsp");
				rd.forward(req, resp);

			} else {
				// req.getSession().invalidate();
				System.out.println("Utente già esistente");
				RequestDispatcher rd = req.getRequestDispatcher("error.html");
				rd.forward(req, resp);
			}
		} else {
			System.out.println("Password non corrispondente");
		}

	}
}
