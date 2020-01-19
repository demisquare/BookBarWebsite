package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Menu;
import model.Menu;
import model.Utente;
import persistence.DBManager;
import persistence.DataSource;
import persistence.dao.MenuDAO;
import persistence.dao.UtenteDAO;

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
			String query = "SELECT * FROM public.\"Menu\" WHERE public.\"Menu\".\"MenuID\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, menuid);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
	        menu = new Menu();
	        menu.setId(result.getInt("MenuID"));
	        menu.setName(result.getString("Name"));
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
		List<Menu> studenti = new LinkedList<>();
		try {
			connection = this.dataSource.getConnection();
			Menu menu;
			PreparedStatement statement;
			String query = "select * from menu";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
//        menu = new Menu();
//        menu.setMatricola(result.getString("matricola"));
//        menu.setNome(result.getString("nome"));
//        menu.setCognome(result.getString("cognome"));
//        menu.setDataNascita(result.getString("datanascita"));
//
//        Scuola scuola = DBManager.getInstance().getScuolaDAO().findByPrimaryKey(
//            result.getLong("scuola"));
//        menu.setScuolaDiDiploma(scuola);
//
//        CorsoDiLaurea corsoDiLaurea =
//            DBManager.getInstance().getCorsoDiLaureaDAO().findByPrimaryKey(
//                result.getLong("corsodilaurea"));
//        menu.setCorsoDiLaurea(corsoDiLaurea);
//
//        studenti.add(menu);
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
		return studenti;
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
