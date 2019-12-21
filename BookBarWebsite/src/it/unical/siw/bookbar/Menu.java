package it.unical.siw.bookbar;

import java.util.ArrayList;

public class Menu {
	private String _name;
	private ArrayList<String> _prodotti;
	
	public Menu(String name) {
		_name = name;
		_prodotti = new ArrayList<String>();
	}
	
	public void addProduct(String prodotto) {
		_prodotti.add(prodotto);
	}
	
	public String getName() {
		return _name;
	}
	
}
