package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Menu;
import model.Ordine;
import model.Utente;
import persistence.DBManager;
import persistence.DataSource;
import persistence.dao.OrdineDAO;

public class OrdineDaoJDBC implements OrdineDAO {
	private DataSource dataSource;

	public OrdineDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Ordine ordine) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "INSERT INTO public.\"Order\"(\"UserID\", \"MenuID\", \"Status\") VALUES (?, ?, 'Nuovo')";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, ordine.getUser().getId());
			statement.setInt(2, ordine.getMenu().getId());
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

	public Ordine findByPrimaryKey(int orderid) {
		Connection connection = null;
		Ordine ordine = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM public.\"Order\" WHERE public.\"Order\".\"OrderID\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, orderid);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				ordine = new Ordine();
				ordine.setData(result.getTimestamp("Data").toString());
				ordine.setUser(DBManager.getInstance().getUtenteDAO().findByPrimaryKey(result.getInt("UserID")));
				ordine.setId(result.getInt("OrderID"));
				Menu menu = DBManager.getInstance().getMenuDAO().findByPrimaryKey(result.getInt("MenuID"));
				ordine.setMenu(menu);
//          System.out.println("Menu ID " + ordine.getMenu().getName());
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
		return ordine;
	}

	public List<Ordine> findAll() {
		Connection connection = null;
		List<Ordine> ordini = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Ordine ordine;
			PreparedStatement statement;
			String query = "SELECT * FROM public.\"Order\" ORDER BY \"OrderID\" DESC";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ordine = new Ordine();
				ordine.setStato(result.getString("Status"));
				ordine.setData(result.getTimestamp("Data").toString());
				ordine.setUser(DBManager.getInstance().getUtenteDAO().findByPrimaryKey(result.getInt("UserID")));
				ordine.setId(result.getInt("OrderID"));

				Menu menu = DBManager.getInstance().getMenuDAO().findByPrimaryKey(result.getInt("MenuID"));
				ordine.setMenu(menu);
//        System.out.println("Menu ID " + ordine.getMenu().getName());
				ordini.add(ordine);
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
		return ordini;
	}

	public void update(Ordine ordine) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "UPDATE public.\"Order\" SET \"Status\" = ? WHERE \"OrderID\" = ? ";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ordine.getStato());
			statement.setInt(2, ordine.getId());
			statement.executeUpdate();
			System.out.println("Stamp");
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

	public void delete(Ordine ordine) {
		// Connection connection = null;
		// try {
		// connection = this.dataSource.getConnection();
		// String delete =
		// "DELETE FROM public.\"Order\" WHERE public.\"Order\".\"UserID\" = ? AND
		// public.\"Order\".\"MenuID\" = ? ";
		// PreparedStatement statement = connection.prepareStatement(delete);
		// statement.setString(2, ordine.getUser().getId());
		// statement.setString(2, ordine.getMenu().getId());
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
