package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Chart;
import model.Menu;
import model.Utente;
import persistence.DBManager;

@SuppressWarnings({"serial", "unchecked"})
public final class chart extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		Utente user = null;
		user = (Utente) session.getAttribute("utente");
		if (user != null) {
			DBManager dbm = persistence.DBManager.getInstance();
			List<Menu> menu = new LinkedList<>();
			menu = dbm.getAllMenus();
			HashMap<Integer, Menu> menuMap = new HashMap<Integer, Menu>();
			
			for (int i = 0; i < menu.size(); i++) {
			    Menu m = menu.get(i);
			    menuMap.put(m.getId(), m);
			}
			

			HashMap<Integer, Integer> carrello = (HashMap<Integer, Integer>) session.getAttribute("carrello");
			
			List<Chart> carrelloItems = new LinkedList<>();
			carrello.entrySet().forEach(entry->{
				Menu m = (Menu) menuMap.get(entry.getKey());
				carrelloItems.add(new Chart(m, entry.getValue()));
				System.out.println("menu: " + m.getName() + " - qta: " + entry.getValue());
			});
			
			String json = new Gson().toJson(carrelloItems);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			
			res.getWriter().write(json);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		HashMap<Integer, Integer> carrello = (HashMap<Integer, Integer>) session.getAttribute("carrello");
		System.out.println("                                                                                   elimina menu " + req.getParameter("id"));
		int id = Integer.parseInt(req.getParameter("id"));
		if (session.getAttribute("utente") != null) {
			if(carrello.get(id)!=null) {
				carrello.put(id, carrello.get(id)-1);
				if (carrello.get(id) == 0)  {
					carrello.remove(id);
				}
			} 
			
			int numEleCarrello = (int) session.getAttribute("numEleCarrello");
			numEleCarrello -= 1;
			System.out.println("il carrello contiene " + numEleCarrello + " elementi");
			
			carrello.entrySet().forEach(entry->{
				System.out.println("menu: " + entry.getKey() + " - qta: " + entry.getValue());  
			});
			
			session.setAttribute("numEleCarrello", numEleCarrello);
			session.setAttribute("carrello", carrello);
			System.out.println("il carrello contiene " + numEleCarrello + " elementi");
		}
	}
}