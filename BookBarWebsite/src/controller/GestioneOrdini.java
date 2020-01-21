package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Menu;
import model.Ordine;
import model.Utente;
import persistence.DBManager;

@SuppressWarnings("serial")
public final class GestioneOrdini extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setAttribute("orders", "");
		req.getRequestDispatcher("GestioneOrdini.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String operation = req.getParameter("operation");
		DBManager dbm = persistence.DBManager.getInstance();
		String status = "";
		Ordine ordine = null;
		int id = -1;
		System.out.println("POST ORDINI "+ id + " operazione " + operation);
		if (operation.equals("lavorazione")) {
			id = Integer.parseInt(req.getParameter("id"));
			ordine = dbm.findOrderByPrimaryKey(id);
			// flag "workingon" nel database (update)
			System.out.println("Ordine in lavorazione: " + id);
			status = "Lavorazione";
			ordine.setStato(status);
			dbm.updateOrder(ordine);		
		} else if (operation.equals("consegna")) {
			id = Integer.parseInt(req.getParameter("id"));
			ordine = dbm.findOrderByPrimaryKey(id);

			// flag "consegnato" nel database (update)
			System.out.println("Ordine consegnato: " + id);
			status = "Consegnato";
			ordine.setStato(status);
			dbm.updateOrder(ordine);
		} else if (operation.equals("delete")) {
			id = Integer.parseInt(req.getParameter("id"));
			ordine = dbm.findOrderByPrimaryKey(id);

			// delete nel database
			System.out.println("Ordine da eliminare: " + id);
			//  dbm.deleteOrder(ordine);
		} else if(operation.equals("add")) {
			ordine = new Ordine();
			Utente utente = dbm.getUtenteDAO().findByPrimaryKey(Integer.parseInt(req.getParameter("user_id")));
			Menu menu = dbm.getMenuDAO().findByPrimaryKey(Integer.parseInt(req.getParameter("menu_id")));
			ordine.setUser(utente);
			ordine.setMenu(menu);
			dbm.addOrder(ordine);			
		}
		System.out.println("asfsdfsd");
		res.setStatus(200);
	}
}