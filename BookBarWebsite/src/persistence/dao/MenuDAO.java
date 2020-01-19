package persistence.dao;

import java.util.List;

import model.Menu;

public interface MenuDAO {
	public void save(Menu menu); // Create

	public Menu findByPrimaryKey(int menuid); // Retrieve

	public List<Menu> findAll();

	public void update(Menu menu); // Update

	public void delete(Menu menu); // Delete
}