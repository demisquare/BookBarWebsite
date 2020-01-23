package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			String insert = "INSERT INTO \"public\".\"Menu\" (\"Name\", \"Prezzo\") VALUES (?, ?);";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, menu.getName());
			statement.setDouble(2, menu.getPrezzo());
			statement.executeUpdate();
			
			// manca la parte di aggiunta dei prodotti al menu
			
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
			ResultSet result = statement.executeQuery();
			menu = new Menu();
			menu.setId(menuid);
			while (result.next()) {
				System.out.println("prodotti??????????????????" + menuid);
				menu.setName(result.getString("Name"));

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
				menu.setPrezzo(result.getDouble("Prezzo"));
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
		updateProds(menu);			
//		Connection connection = null;
//		try {
//			connection = this.dataSource.getConnection();
//			String update = "UPDATE public.\"Menu\" SET public.\"Menu\".\"Descrizione\" = ? WHERE public.\"Menu\".\"MenuID\" = ? ";
//			PreparedStatement statement = connection.prepareStatement(update);
//			statement.setString(1, menu.getDescrizione());
//			statement.setInt(2, menu.getId());
//			statement.executeUpdate();
//		} catch (SQLException e) {
//			throw new RuntimeException(e.getMessage());
//		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
//		}
	}

	private void updateProds(Menu menu) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			Menu menuVecchio = findByPrimaryKey(menu.getId());
			
			String delete = "DELETE FROM public.\"MenuProduct\" WHERE public.\"MenuProduct\".\"MenuID\" = ? AND public.\"MenuProduct\".\"ProductID\" = ?";
			for (int i = 0; i < menuVecchio.getProdotti().size(); i++) {
				PreparedStatement statementDelete = connection.prepareStatement(delete);
				statementDelete.setInt(1, menu.getId());
				statementDelete.setInt(2, menuVecchio.getProdotti().get(i).getId());
				System.out.println("Prodotto eliminato " + i + ": " + menuVecchio.getProdotti().get(i).getNome());
				statementDelete.executeUpdate();
			}
			
			String update = "INSERT INTO public.\"MenuProduct\" (\"MenuID\", \"ProductID\", \"Price\") VALUES (?, ?, ?)";
			for (int i = 0; i < menu.getProdotti().size(); i++) {
				PreparedStatement statementUpdate = connection.prepareStatement(update);
				statementUpdate.setInt(1, menu.getId());
				statementUpdate.setInt(2, menu.getProdotti().get(i).getId());
				statementUpdate.setInt(3, (int) menu.getProdotti().get(i).getPrezzo());
				System.out.println("Prodotto aggiunto " + i + ": " + menu.getProdotti().get(i).getNome());
				statementUpdate.executeUpdate();
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
