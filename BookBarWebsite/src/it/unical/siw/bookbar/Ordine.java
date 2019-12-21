package it.unical.siw.bookbar;

public class Ordine {
	private String stato;
	private int id;
	private String data;
	private String username;
	private String menu;
	
	public Ordine(String stato, int id, String data, String username, String menu) {
		this.stato = stato;
		this.id = id;
		this.data = data;
		this.username = username;
		this.menu = menu;
	}

	public String getUsername() {
		return username;
	}
	
	public int getId() {
		return id;
	}

	public String getStato() {
		return stato;
	}

	public String getData() {
		return data;
	}

	public String getMenu() {
		return menu;
	}

	
}
