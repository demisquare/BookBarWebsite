package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Menu;
import model.Ordine;
import model.Prodotto;
import model.Utente;
import persistence.DBManager;
import persistence.DataSource;
import persistence.dao.OrdineDAO;
import persistence.dao.ProdottoDAO;
import persistence.dao.UtenteDAO;

public class ProdottoDaoJDBC implements ProdottoDAO {
	private DataSource dataSource;

	public ProdottoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Prodotto prodotto) {
		// Connection connection = null;
		// try {
		// connection = this.dataSource.getConnection();
		// String insert =
		// "insert into prodotto(matricola, nome, cognome,"
		// + " datanascita, scuola, corsodilaurea) values (?,?,?,?,?,?)";
		// PreparedStatement statement = connection.prepareStatement(insert);
		// statement.setString(1, prodotto.getMatricola());
		// statement.setString(2, prodotto.getNome());
		// statement.setString(3, prodotto.getCognome());
		// statement.setString(4, prodotto.getDataNascita());
		// statement.setLong(5, prodotto.getScuolaDiDiploma().getId());
		// // statement.setLong(5,
		// // prodotto.get.getScuolaDiDiploma().getId());
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

	public Prodotto findByPrimaryKey(int prodid) {
		Connection connection = null;
		Prodotto prodotto = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM public.\"Order\" WHERE public.\"Order\".\"UserID\" = ? AND public.\"Order\".\"MenuID\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, prodid);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
//        prodotto = new Prodotto();
//        prodotto.setMatricola(result.getString("UserID"));
//        prodotto.setNome(result.getString("MenuID"));
//
//        Utente utente = DBManager.getInstance().getUtenteDAO().findByPrimaryKey(
//            result.getInt("UserID"));
//				prodotto.setUser(utente);
//        
//        Menu menu = DBManager.getInstance().getMenuDAO().findByPrimaryKey(
//            result.getInt("MenuID"));
//        prodotto.setMenu(menu);
//        
//		prodotto.setSatus(result.getString("Status"));
//        prodotto.setData(result.getString("Data"));
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
			String query = "select * from prodotto";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
//        prodotto = new Prodotto();
//        prodotto.setMatricola(result.getString("matricola"));
//        prodotto.setNome(result.getString("nome"));
//        prodotto.setCognome(result.getString("cognome"));
//        prodotto.setDataNascita(result.getString("datanascita"));
//
//        Scuola scuola = DBManager.getInstance().getScuolaDAO().findByPrimaryKey(
//            result.getLong("scuola"));
//        prodotto.setScuolaDiDiploma(scuola);
//
//        CorsoDiLaurea corsoDiLaurea =
//            DBManager.getInstance().getCorsoDiLaureaDAO().findByPrimaryKey(
//                result.getLong("corsodilaurea"));
//        prodotto.setCorsoDiLaurea(corsoDiLaurea);
//
//        prodotti.add(prodotto);
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
