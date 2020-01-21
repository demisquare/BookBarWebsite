package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Menu;
import model.Prodotto;
import persistence.DBManager;
import persistence.DataSource;
import persistence.dao.MenuDAO;

public class MenuDaoJDBC implements MenuDAO {
	private DataSource dataSource;

	public MenuDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Menu menu) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "INSERT INTO \"public\".\"Menu\" (\"Name\") VALUES (?);";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, menu.getName());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public Menu findByPrimaryKey(int menuid) {
		Connection connection = null;
		Menu menu = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM public.\"MenuProduct\" as mp JOIN public.\"Menu\" as m ON mp.\"MenuID\" = m.\"MenuID\" AND m.\"MenuID\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, menuid);
			double prezzo = 0.0;
			ResultSet result = statement.executeQuery();
			menu = new Menu();
			menu.setId(menuid);
			while (result.next()) {
				System.out.println("prodotti??????????????????" + menuid);
				
				Prodotto prodotto = DBManager.getInstance().getProdottoDAO()
						.findByPrimaryKey(result.getInt("ProductID"));
				menu.addProdotto(prodotto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return menu;
	}

	public List<Menu> findAll() {
		Connection connection = null;
		List<Menu> menues = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Menu menu;
			PreparedStatement statement;
			String query = "select * from \n" + "	public.\"Menu\" GROUP BY \"MenuID\" ";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				int id = result.getInt("MenuID");
				menu = findByPrimaryKey(id);
				menu.setName(result.getString("Name"));
				System.out.println(menu.getId() + " " + menu.getName());
				menues.add(menu);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return menues;
	}

	public void update(Menu menu) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "UPDATE public.\"Menu\" SET public.\"Menu\".\"Descrizione\" = ? WHERE public.\"Menu\".\"MenuID\" = ? ";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, menu.getDescrizione());
			statement.setInt(2, menu.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public void delete(Menu menu) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM public.\"Menu\" WHERE public.\"Menu\".\"MenuID\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, menu.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}
