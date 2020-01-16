package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

//import model.CorsoDiLaurea;
//import model.Scuola;
import model.Utente;
import persistence.DataSource;
import persistence.dao.UtenteDAO;

public class UtenteDaoJDBC implements UtenteDAO {
	private DataSource dataSource;

	public UtenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Utente utente) {
//		Connection connection = null;
//		try {
//			connection = this.dataSource.getConnection();
//			String insert = "insert into studente(matricola, nome, cognome,"
//					+ " datanascita, scuola, corsodilaurea) values (?,?,?,?,?,?)";
//			PreparedStatement statement = connection.prepareStatement(insert);
//			statement.setString(1, studente.getMatricola());
//			statement.setString(2, studente.getNome());
//			statement.setString(3, studente.getCognome());
//			statement.setString(4, studente.getDataNascita());
//			statement.setLong(5, studente.getScuolaDiDiploma().getId());
////			statement.setLong(5, studente.get.getScuolaDiDiploma().getId());
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

	public Utente findByPrimaryKey(String matricola) {
//		Connection connection = null;
//		Studente studente = null;
//		try {
//			connection = this.dataSource.getConnection();
//			PreparedStatement statement;
//			String query = "select * from studente where matricola = ?";
//			statement = connection.prepareStatement(query);
//			statement.setString(1, matricola);
//			ResultSet result = statement.executeQuery();
//			if (result.next()) {
//				studente = new Studente();
//				studente.setMatricola(result.getString("matricola"));				
//				studente.setNome(result.getString("nome"));
//				studente.setCognome(result.getString("cognome"));
//				studente.setDataNascita(result.getString("datanascita"));
//				
//				Scuola scuola = DBManager.getInstance().getScuolaDAO().findByPrimaryKey(result.getLong("scuola"));
//				studente.setScuolaDiDiploma(scuola);
//				
//				CorsoDiLaurea corsoDiLaurea = DBManager.getInstance().getCorsoDiLaureaDAO().findByPrimaryKey(result.getLong("corsodilaurea"));
//				studente.setCorsoDiLaurea(corsoDiLaurea);
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e.getMessage());
//		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				throw new RuntimeException(e.getMessage());
//			}
//		}	
//		return studente;
		return null;
	}

	public List<Utente> findAll() {
		Connection connection = null;
		List<Utente> utenti = new LinkedList<>();

		try {
			connection = this.dataSource.getConnection();
			System.out.println("  -------> Connected to the PostgreSQL server successfully.");
			Utente utente;
			PreparedStatement statement;
			String query = "select * from public.users";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				utente = new Utente();
				utente.setUsername(result.getString("username"));
				utente.setPassword(result.getString("password"));
				System.out.println("\t\t username " + utente.getUsername());

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
//		Connection connection = null;
//		try {
//			connection = this.dataSource.getConnection();
//			String update = "update studente SET nome = ?, cognome = ?, data_nascita = ?, scuola = ?, corsodilaurea = ? WHERE matricola=?";
//			PreparedStatement statement = connection.prepareStatement(update);
//			statement.setString(1, studente.getNome());
//			statement.setString(2, studente.getCognome());
//			statement.setString(3, studente.getDataNascita());			
//			statement.setLong(4, studente.getScuolaDiDiploma().getId());
//			statement.setLong(5, studente.getCorsoDiLaurea().getId());
//			statement.setString(6, studente.getMatricola());
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

	public void delete(Utente utente) {
//		Connection connection = null;
//		try {
//			connection = this.dataSource.getConnection();
//			String delete = "delete FROM studente WHERE matricola = ? ";
//			PreparedStatement statement = connection.prepareStatement(delete);
//			statement.setString(1, studente.getMatricola());
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

}
