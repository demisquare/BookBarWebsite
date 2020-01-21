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
		// Connection connection = null;
		// try {
		// connection = this.dataSource.getConnection();
		// String insert =
		// "insert into menu(matricola, nome, cognome,"
		// + " datanascita, scuola, corsodilaurea) values (?,?,?,?,?,?)";
		// PreparedStatement statement = connection.prepareStatement(insert);
		// statement.setString(1, menu.getMatricola());
		// statement.setString(2, menu.getNome());
		// statement.setString(3, menu.getCognome());
		// statement.setString(4, menu.getDataNascita());
		// statement.setLong(5, menu.getScuolaDiDiploma().getId());
		// // statement.setLong(5,
		// // menu.get.getScuolaDiDiploma().getId());
		// statement.executeUpdate();
		// } catch (SQLException e) {
		// throw new RuntimeException(e.getMessage());
		// } finally {
		// try {
		// connection.close();
		// } catch (SQLException e) {
		// throw new RuntimeException(e.getMessage());
		// }
		// }
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
			while(result.next()) {
		        menu.setId(result.getInt("MenuID"));
		        menu.setName(result.getString("Name"));
		        Prodotto prodotto = DBManager.getInstance().getProdottoDAO().findByPrimaryKey(result.getInt("ProductID"));
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
			String query = "SELECT \"MenuID\" AS id FROM public.\"Menu\" GROUP BY \"MenuID\" ";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
		        int id = result.getInt("id");
		        System.out.println(id);	
		        menu = findByPrimaryKey(id);
		        System.out.println(menu.getName());
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
		// Connection connection = null;
		// try {
		// connection = this.dataSource.getConnection();
		// String update =
		// "UPDATE public.\"Order\" SET public.\"Order\".\"Status\" = ? WHERE
		// public.\"Order\".\"UserID\" = ? AND public.\"Order\".\"MenuID\" = ? ";
		// PreparedStatement statement = connection.prepareStatement(update);
		// statement.setString(1, menu.getStatus());
		// statement.setString(2, menu.getUser().getId());
		// statement.setString(2, menu.getMenu().getId());
		// statement.executeUpdate();
		// } catch (SQLException e) {
		// throw new RuntimeException(e.getMessage());
		// } finally {
		// try {
		// connection.close();
		// } catch (SQLException e) {
		// throw new RuntimeException(e.getMessage());
		// }
		// }
	}

	public void delete(Menu menu) {
		// Connection connection = null;
		// try {
		// connection = this.dataSource.getConnection();
		// String delete =
		// "DELETE FROM public.\"Order\" WHERE public.\"Order\".\"UserID\" = ? AND
		// public.\"Order\".\"MenuID\" = ? ";
		// PreparedStatement statement = connection.prepareStatement(delete);
		// statement.setString(2, menu.getUser().getId());
		// statement.setString(2, menu.getMenu().getId());
		// statement.executeUpdate();
		// } catch (SQLException e) {
		// throw new RuntimeException(e.getMessage());
		// } finally {
		// try {
		// connection.close();
		// } catch (SQLException e) {
		// throw new RuntimeException(e.getMessage());
		// }
		// }
	}
}
