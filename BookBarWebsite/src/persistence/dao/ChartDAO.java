package persistence.dao;

import java.util.List;

import model.Chart;

public interface ChartDAO {
	public void save(Chart chart); // Create

	public Chart findByPrimaryKey(int userid, int productid); // Retrieve

	public List<Chart> findAll();

	public void update(Chart chart); // Update

	public void delete(Chart chart); // Delete
}