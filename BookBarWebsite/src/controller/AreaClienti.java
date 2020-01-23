package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Utente;
import persistence.DBManager;

@SuppressWarnings("serial")
public class AreaClienti extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("UserSettings.jsp");
		rd.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String operation = req.getParameter("operation");
		HttpSession session = req.getSession(false);
		

		if (session.getAttribute("utente") != null) {
			
		Utente utente = (Utente) session.getAttribute("utente");
		
		if (operation.equals("get")) {
			String json = new Gson().toJson(utente);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);
			
			
			
		} else if (operation.equals("update")) {
			String password = req.getParameter("inputOldPassword");
			System.out.println("Soggetto da modificare | password" + password);
			if (!utente.getPassword().equals(password)) {
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write("{err: \"Password non corrispondente\"}");
				System.out.println("Soggetto da modificare | password errata");
				return;
			}
			
			/*
			 * 			
			 * 
			inputFN: inputFN.value,
			inputLN: inputLN.value,
			inputEmail: inputEmail.value,
			inputOldPassword: inputOldPassword.value,
			inputNewPassword: inputNewPassword.value,
			 * 
			 * 
			 * 
			 * */
			
			
			String firstname = req.getParameter("inputFN");
			String lastname = req.getParameter("inputLN");
			String email = req.getParameter("inputEmail");
			String passwordNuova = req.getParameter("inputNewPassword");
			utente.setFirstName(firstname);
			utente.setLastName(lastname);
			utente.setEmail(email);

			if(!passwordNuova.equals("")) utente.setPassword(passwordNuova);
			
			System.out.println("Soggetto da modificare | email " + email + " password " + password + " firstname " + firstname + " lastname " + lastname);
			DBManager dbm = persistence.DBManager.getInstance();
			dbm.getUtenteDAO().update(utente);
			res.setStatus(200);
		}
		}
	}
}
