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

//		// Codice di prova, da eliminare
//		Utente utonto = new Utente();
//		utonto.setFirstName("test");
//		utonto.setRole("admin");
		// utonto.setRole("user");
//		HttpSession session = req.getSession(false);
//		session.setAttribute("utente", utonto);
		// ====================================
		// System.out.println("gfjldkgjfkldgj");
		
		//HttpSession session = req.getSession();

		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("compra menu 1...");
		
		HttpSession session = req.getSession(false);
		
		HashMap<Integer, Integer> carrello = (HashMap<Integer, Integer>) session.getAttribute("carrello");
		
		if(carrello.get(1)!=null) {
			carrello.put(1, carrello.get(1)+1);
			int numEleCarrello = (int) session.getAttribute("numEleCarrello");
			numEleCarrello += 1;
			session.setAttribute("numEleCarrello", numEleCarrello);
			
		} else {
			carrello.put(1, 1);
		}

		System.out.println("il carrello contiene: ");
		
		carrello.entrySet().forEach(entry->{
		    System.out.println("menu: " + entry.getKey() + " - qta: " + entry.getValue());  
		 });
		
		
//		session.setAttribute("carrello", carrello);
	}
}
