package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.DBManager;

@SuppressWarnings("serial")
public final class testConnessioneDB extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		DBManager dbm = persistence.DBManager.getInstance();
		List<Utente> utenti = new LinkedList<>();
		utenti = dbm.getAllUsers();
		ArrayList<Integer> numeri = new ArrayList<>();
		numeri.add(1);
		numeri.add(2);
		numeri.add(3);
		req.setAttribute("utentiPresenti", utenti.size());
		req.setAttribute("numeri", numeri);
		req.setAttribute("utenti", utenti);
		RequestDispatcher view = req.getRequestDispatcher("testConnessioneDB.jsp");
		view.forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	}
}