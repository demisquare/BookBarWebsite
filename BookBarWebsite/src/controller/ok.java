package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Chart;
import model.Menu;
import model.Ordine;
import model.Utente;
import persistence.DBManager;

@SuppressWarnings("serial")
public class ok extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sessione = req.getSession(false);
		if(sessione != null) {
		DBManager dbm = persistence.DBManager.getInstance();
		Utente utente = (Utente) sessione.getAttribute("utente");
		
		HashMap<Integer, Integer> carrello = (HashMap<Integer, Integer>) sessione.getAttribute("carrello");

		carrello.entrySet().forEach(entry->{
			Menu menu = dbm.getMenuDAO().findByPrimaryKey(entry.getKey());
			Ordine ordine = new Ordine();
			ordine.setUser(utente);
			ordine.setMenu(menu);
			dbm.addOrder(ordine);
		});
		sessione.removeAttribute("carrello");
		}
		RequestDispatcher rd = req.getRequestDispatcher("ok.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
