package persistence.dao;

import java.util.List;

import model.Ordine;

public interface OrdineDAO {
	public void save(Ordine ordine); // Create

	public Ordine findByPrimaryKey(String userid, String menuid); // Retrieve

	public List<Ordine> findAll();

	public void update(Ordine ordine); // Update

	public void delete(Ordine ordine); // Delete
}