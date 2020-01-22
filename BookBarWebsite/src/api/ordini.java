package api;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ordine;
import model.Utente;
import persistence.DBManager;

@SuppressWarnings("serial")
public final class ordini extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		DBManager dbm = persistence.DBManager.getInstance();
		List<Ordine> ordini = new LinkedList<>();
		
		ordini = dbm.getAllOrders();

		String json = new Gson().toJson(ordini);

		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(json);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if (req.getSession(false).getAttribute("utente") != null) {
		Utente user = (Utente) req.getSession(false).getAttribute("utente");
		DBManager dbm = persistence.DBManager.getInstance();
		List<Ordine> ordini = new LinkedList<>();
		
		ordini = dbm.getAllUserOrders(user);

		String json = new Gson().toJson(ordini);

		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(json);		
		}
	}
}