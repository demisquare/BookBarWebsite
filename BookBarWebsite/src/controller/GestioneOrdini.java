package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ordine;

@SuppressWarnings("serial")
public final class GestioneOrdini extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		// placeholder da eliminare
		// public Ordine(String stato, int id, String data, String username, String
		// menu) {
		ArrayList<Ordine> orders = new ArrayList<Ordine>();
		orders.add(new Ordine("Nuovo", 0, "21 dic 2019 21:53", "user 01", "Menu 02"));
		orders.add(new Ordine("Nuovo", 1, "21 dic 2019 21:47", "user 02", "Menu 01"));
		orders.add(new Ordine("Nuovo", 2, "21 dic 2019 21:23", "user 00", "Menu 01"));
		// ==========

		req.setAttribute("orders", orders);
		req.getRequestDispatcher("GestioneOrdini.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String operation = req.getParameter("operation");
		int id = Integer.parseInt(req.getParameter("id"));

		switch (operation) {
		case "lavorazione":
			// flag "workingon" nel database (update)
			System.out.println("Ordine in lavorazione: " + id);
			break;
		case "consegna":
			// flag "consegnato" nel database (update)
			System.out.println("Ordine consegnato: " + id);
			break;
		case "del":
			// delete nel database
			System.out.println("Ordine da eliminare: " + id);
			break;
		}

		res.setStatus(200);
	}
}