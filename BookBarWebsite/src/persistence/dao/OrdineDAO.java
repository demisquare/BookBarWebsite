package persistence.dao;

import java.util.List;

import model.Ordine;
import model.Utente;

public interface OrdineDAO {
	public void save(Ordine ordine); // Create

	public Ordine findByPrimaryKey(int orderid); // Retrieve

	public List<Ordine> findAll();
	public List<Ordine> findAllByUser(Utente utente);

	public void update(Ordine ordine); // Update

	public void delete(Ordine ordine); // Delete
}