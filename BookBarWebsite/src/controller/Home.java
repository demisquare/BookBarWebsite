package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;

public class Home extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");

		// Codice di prova, da eliminare
		Utente utonto = new Utente();
		utonto.setFirstName("test");
		utonto.setRole("admin");
		// utonto.setRole("user");
		HttpSession session = req.getSession();
		session.setAttribute("utente", utonto);
		// ====================================
		// System.out.println("gfjldkgjfkldgj");
		
		//HttpSession session = req.getSession();
		session.setAttribute("carrello", new HashMap<Integer, Integer>());
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("compra menu 1...");
		
		HttpSession session = req.getSession();
		HashMap<Integer, Integer> carrello = (HashMap<Integer, Integer>) session.getAttribute("carrello");
		
		if(carrello.get(1)!=null)
			carrello.put(1, carrello.get(1)+1);
		else
			carrello.put(1, 1);

		System.out.println("il carrello contiene: ");
		
		carrello.entrySet().forEach(entry->{
		    System.out.println("menu: " + entry.getKey() + " - qta: " + entry.getValue());  
		 });
		
		doGet(req, resp);
	}
}
