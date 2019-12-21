package it.unical.siw.bookbar;

public class Prodotto {
	private String nome;
	private int id;
	private String descrizione;
	private double prezzo;
	private String deadline;
	
	public Prodotto(String nome, int id, String descrizione, double prezzo) {
		this.nome = nome;
		this.id = id;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	
}
