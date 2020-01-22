package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Menu;
import model.Prodotto;
import persistence.DBManager;

@SuppressWarnings("serial")

public final class GestioneMenu extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setAttribute("menu", "Esempio");
		req.getRequestDispatcher("GestioneMenu.jsp").forward(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		DBManager dbm = persistence.DBManager.getInstance();
		String operation = req.getParameter("operation");
		String subject = req.getParameter("subject");
		int id = -1;
		if (subject.equalsIgnoreCase("product")) {
			Prodotto prodotto = new Prodotto();
			if (operation.equals("add")) {
				System.out.println("add product");
				prodotto.setNome(req.getParameter("name"));
				prodotto.setDescrizione(req.getParameter("desc"));
				dbm.addProd(prodotto);	
			} else if (operation.equals("del")) {
				id = Integer.parseInt(req.getParameter("id"));
				prodotto = dbm.findProdByPrimaryKey(id);
				System.out.println("del product" + prodotto.getNome());
				dbm.deleteProd(prodotto);
			}
		} else if (subject.equalsIgnoreCase("menu")) {
			Menu menu = new Menu(); 
			if (operation.equals("add")) {
				System.out.println("add menu");
				menu.setName(req.getParameter("name"));
				menu.setDescrizione(req.getParameter("desc"));
				dbm.addMenu(menu);	
			} else if (operation.equals("del")) {
				id = Integer.parseInt(req.getParameter("id"));
				// flag "consegnato" nel database (update)
				menu = dbm.findMenuByPrimaryKey(id);
				int qtaProdotti = menu.getProdotti().size();
				if (qtaProdotti == 0) {
					System.out.println("del menu " + id);
					dbm.deleteMenu(menu);					
				} else {
					System.out.println("Menu non vuoto");
				}
			} else if (operation.equals("update")) {
				id = Integer.parseInt(req.getParameter("id"));
				menu = dbm.findMenuByPrimaryKey(id);
				
		        String x = req.getParameterValues("newProds")[0];				
				
		        menu.svouta();
		        
		        System.out.println("stringa " + x);
		        if (x.length() != 2) {
		        	String newProd = x.substring(1, x.length()-1);
		        	String[] tokens=newProd.split(",");
		        	
		        	for (int i = 0; i < tokens.length; i++) {
		        		Prodotto p = dbm.getProdottoDAO().findByPrimaryKey(Integer.parseInt(tokens[i]));
		        		System.out.println("Mehhhhh  " + tokens[i]);
		        		menu.addProdotto(p);
		        	}
		        }
				dbm.updateMenu(menu);				
			}
		}
		res.setStatus(200);
	}
}