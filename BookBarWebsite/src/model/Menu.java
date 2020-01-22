package model;

import java.util.ArrayList;

public class Menu {
	private int id;
	private String name;
	private String descrizione;

	private ArrayList<Prodotto> prodotti;
	private double prezzo;

	public Menu() {
		this.id = -1;
		this.prodotti = new ArrayList<Prodotto>();
		this.prezzo = 0;
		this.name = "";
	}

	// public Menu(int id, String name, ArrayList<Prodotto> prodotti, double prezzo)
	// {
	// this.id = id;
	// this.name = name;
	// this.prodotti = prodotti;
	// this.prezzo = prezzo;
	// }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getName() {
		return name;
	}

	public void svouta() {
		prodotti.clear();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(ArrayList<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public void addProdotto(Prodotto prodotto) {
		// TODO Auto-generated method stub
		this.prodotti.add(prodotto);
	}

}
