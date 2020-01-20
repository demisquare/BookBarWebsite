package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Prodotto;
import persistence.DataSource;
import persistence.dao.ProdottoDAO;

public class ProdottoDaoJDBC implements ProdottoDAO {
	private DataSource dataSource;

	public ProdottoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Prodotto prodotto) {}

	public Prodotto findByPrimaryKey(int prodid) {
		Connection connection = null;
		Prodotto prodotto = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM public.\"Product\" WHERE public.\"Product\".\"ProductID\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, prodid);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
	        prodotto = new Prodotto();
	        prodotto.setId(result.getInt("ProductID"));
	        prodotto.setNome(result.getString("Name"));
	        prodotto.setDescrizione(result.getString("Description"));
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
		return prodotto;
	}

	public List<Prodotto> findAll() {
		Connection connection = null;
		List<Prodotto> prodotti = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Prodotto prodotto;
			PreparedStatement statement;
			String query = "select * from public.\"Product\"";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
		        prodotto = new Prodotto();
		        prodotto.setId(result.getInt("ProductID"));
		        prodotto.setNome(result.getString("Name"));
		        prodotto.setDescrizione(result.getString("Description"));
	        prodotti.add(prodotto);
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
		return prodotti;
	}

	public void update(Prodotto prodotto) {
		// Connection connection = null;
		// try {
		// connection = this.dataSource.getConnection();
		// String update =
		// "UPDATE public.\"Order\" SET public.\"Order\".\"Status\" = ? WHERE
		// public.\"Order\".\"UserID\" = ? AND public.\"Order\".\"MenuID\" = ? ";
		// PreparedStatement statement = connection.prepareStatement(update);
		// statement.setString(1, prodotto.getStatus());
		// statement.setString(2, prodotto.getUser().getId());
		// statement.setString(2, prodotto.getMenu().getId());
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

	public void delete(Prodotto prodotto) {
		// Connection connection = null;
		// try {
		// connection = this.dataSource.getConnection();
		// String delete =
		// "DELETE FROM public.\"Order\" WHERE public.\"Order\".\"UserID\" = ? AND
		// public.\"Order\".\"MenuID\" = ? ";
		// PreparedStatement statement = connection.prepareStatement(delete);
		// statement.setString(2, prodotto.getUser().getId());
		// statement.setString(2, prodotto.getMenu().getId());
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
