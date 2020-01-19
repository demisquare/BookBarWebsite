package persistence.dao;

import java.util.List;

import model.Prodotto;

public interface ProdottoDAO {
	public void save(Prodotto prodotto); // Create

	public Prodotto findByPrimaryKey(int productid); // Retrieve

	public List<Prodotto> findAll();

	public void update(Prodotto prodotto); // Update

	public void delete(Prodotto prodotto); // Delete
}