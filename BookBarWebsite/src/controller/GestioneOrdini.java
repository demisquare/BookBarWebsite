package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public final class GestioneOrdini extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setAttribute("orders", "");
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