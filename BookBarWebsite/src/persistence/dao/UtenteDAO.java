package persistence.dao;

import java.util.List;

import model.Utente;

public interface UtenteDAO {
	public void save(Utente utente); // Create

	public Utente findByPrimaryKey(int subject); // Retrieve
	
	public Utente findByCredentials(String email, String password); // Retrieve

	public List<Utente> findAll();

	public void update(Utente utente); // Update

	public void delete(Utente utente); // Delete

}