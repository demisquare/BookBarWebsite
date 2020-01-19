package model;

public class Ordine {
	private String stato;
	private String data;
	private Utente user;
	private Menu menu;

	public Ordine() {
		this.stato = "Nuovo";
		this.data = "";
		this.user = null;
		this.menu = null;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Utente getUser() {
		return user;
	}

	public void setUser(Utente user) {
		this.user = user;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
