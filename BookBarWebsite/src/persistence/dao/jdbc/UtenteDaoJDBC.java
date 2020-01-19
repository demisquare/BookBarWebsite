package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Utente;
import persistence.DataSource;
import persistence.dao.UtenteDAO;

public class UtenteDaoJDBC implements UtenteDAO {
	private DataSource dataSource;

	public UtenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Utente utente) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "INSERT INTO public.\"User\" (\"Email\", \"Password\", \"FirstName\", \"LastName\", \"UserType\") VALUES (?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, utente.getEmail());
			statement.setString(2, utente.getPassword());
			statement.setString(3, utente.getFirstName());
			statement.setString(4, utente.getLastName());
			statement.setString(5, utente.getRole());
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

	public Utente findByPrimaryKey(int id) {
		Connection connection = null;
		Utente utente = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM public.\"User\" WHERE public.\"User\".\"UserID\" = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				utente = new Utente();
				utente.setId(result.getInt("UserID"));
				utente.setRole(result.getString("UserType"));
				utente.setFirstName(result.getString("FirstName"));
				utente.setEmail(result.getString("Email"));
				utente.setPassword(result.getString("Password"));
				utente.setLastName(result.getString("LastName"));
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
		return utente;
	}

	public Utente findByCredentials(String email, String password) {
		Connection connection = null;
		Utente utente = null;
		try {
			connection = this.dataSource.getConnection();
			PreparedStatement statement;
			String query = "SELECT * FROM public.\"User\" WHERE public.\"User\".\"Email\" = ? AND public.\"User\".\"Password\" = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				utente = new Utente();
				utente.setId(result.getInt("UserID"));
				utente.setRole(result.getString("UserType"));
				utente.setFirstName(result.getString("FirstName"));
				utente.setEmail(result.getString("Email"));
				utente.setPassword(result.getString("Password"));
				utente.setLastName(result.getString("LastName"));
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
		return utente;
	}
	
	public List<Utente> findAll() {
		Connection connection = null;
		List<Utente> utenti = new LinkedList<>();

		try {
			connection = this.dataSource.getConnection();
			System.out.println("  -------> Connected to the PostgreSQL server successfully.");
			Utente utente;
			PreparedStatement statement;
			String query = "SELECT * FROM public.\"User\"";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				utente = new Utente();
				utente.setId(result.getInt("UserID"));
				utente.setRole(result.getString("UserType"));
				utente.setFirstName(result.getString("FirstName"));
				utente.setEmail(result.getString("Email"));
				utente.setPassword(result.getString("Password"));
				utente.setLastName(result.getString("LastName"));
				utenti.add(utente);
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
		System.out.println("\t\tLa lista degli utenti e lunga " + utenti.size());
		return utenti;
	}

	public void update(Utente utente) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String update = "UPDATE public.\"User\" SET Email = ?, FirstName = ?, LastName = ?, UserType = ? , WHERE UserID = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, utente.getEmail());
			statement.setString(2, utente.getFirstName());
			statement.setString(3, utente.getLastName());
			statement.setString(4, utente.getRole());
			statement.setInt(5, utente.getId());
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

	public void delete(Utente utente) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String delete = "DELETE FROM public.\"User\" WHERE public.\"User\".\"UserID\" = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, utente.getId());
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
