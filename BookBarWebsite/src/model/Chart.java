package model;

public class Chart {
	private Utente utente;
	private Prodotto prodotto;
	private int quantita;
	
	public Chart(Utente utente, Prodotto prodotto) {
		this.utente = utente;
		this.prodotto = prodotto;
		this.quantita = 0;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}
