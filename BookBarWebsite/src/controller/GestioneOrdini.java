package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int id = Integer.parseInt(req.getParameter("id"));
		Ordine ordine = dbm.findOrderByPrimaryKey(id);
		String status = "";
		System.out.println("POST ORDINI "+ id + " operazione " + operation);
		if (operation.equals("lavorazione")) {
			// flag "workingon" nel database (update)
			System.out.println("Ordine in lavorazione: " + id);
			status = "Lavorazione";
			ordine.setStato(status);
			dbm.updateOrder(ordine);		
		} else if (operation.equals("consegna")) {
			// flag "consegnato" nel database (update)
			System.out.println("Ordine consegnato: " + id);
			status = "Consegnato";
			ordine.setStato(status);
			dbm.updateOrder(ordine);
		} else if (operation.equals("delete")) {
			// delete nel database
			System.out.println("Ordine da eliminare: " + id);
			//  dbm.deleteOrder(ordine);
		}
		System.out.println("asfsdfsd");
		res.setStatus(200);
	}
}