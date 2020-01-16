package persistence;

import model.Utente;
import persistence.dao.UtenteDAO;
import persistence.dao.jdbc.UtenteDaoJDBC;

import java.util.List;

public class DBManager {

	private final static String url = "jdbc:postgresql://localhost/bb";
	private final static String user = "bb";
	private final static String password = "bb";

	private static DataSource dataSource;

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			dataSource = new DataSource(url, user, password);
			System.out.println("    Connessione al DB ok\n");
		} catch (Exception e) {
			System.err.println("    PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	public static DBManager instance = null;

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
	}

	public int getCountAllUsers() {
		return 1000;
	}

	public List<Utente> getAllUsers() {
		return getUtenteDAO().findAll();
	}

	public UtenteDAO getUtenteDAO() {
		return new UtenteDaoJDBC(dataSource);
	}
}
