package persistence;

import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import model.Menu;
import model.Ordine;
import model.Prodotto;
import model.Utente;
import persistence.dao.MenuDAO;
import persistence.dao.OrdineDAO;
import persistence.dao.ProdottoDAO;
import persistence.dao.UtenteDAO;
import persistence.dao.jdbc.MenuDaoJDBC;
import persistence.dao.jdbc.OrdineDaoJDBC;
import persistence.dao.jdbc.ProdottoDaoJDBC;
import persistence.dao.jdbc.UtenteDaoJDBC;

public class DBManager {
	static Dotenv dotenv = Dotenv.configure().load();

	// DB Locale
	 private final static String url = "jdbc:postgresql://localhost/bb";
	 private final static String user = "bb";
	 private final static String password = "bb";

	// DB Online
	// private final static String url =
	// "jdbc:postgresql://rajje.db.elephantsql.com/scsobnvn";
	// private final static String user = "scsobnvn";
	// private final static String password = "eyWmoMiquMNtJlm4yW75U0_004k0Tcq9";

//	private final static String url = dotenv.get("DB_URL");
//	private final static String user = dotenv.get("DB_USER");
//	private final static String password = dotenv.get("DB_PASS");

	private static DataSource dataSource;

	static {
		try {
			System.out.println("DATI LETTI da .env ------------ " + url + " " + user + " " + password);
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

	// UTENTI //
	public Utente findUserByPrimaryKey(int subject) {
		return getUtenteDAO().findByPrimaryKey(subject);
	}

	public Utente findUserByCredentials(String email, String password) {
		return getUtenteDAO().findByCredentials(email, password);
	}

	public int getCountAllUsers() {
		return 1000;
	}

	public List<Utente> getAllUsers() {
		return getUtenteDAO().findAll();
	}

	public void addUser(Utente utente) {
		getUtenteDAO().save(utente);
	}

	public void deleteUser(Utente utente) {
		getUtenteDAO().delete(utente);
	}

	public UtenteDAO getUtenteDAO() {
		return new UtenteDaoJDBC(dataSource);
	}

	// ORDINI //
	public Ordine findOrderByPrimaryKey(int orderid) {
		return getOrdineDAO().findByPrimaryKey(orderid);
	}

	public List<Ordine> getAllOrders() {
		return getOrdineDAO().findAll();
	}

	public void addOrder(Ordine ordine) {
		getOrdineDAO().save(ordine);
	}

	public void deleteOrder(Ordine ordine) {
		getOrdineDAO().delete(ordine);
	}

	public OrdineDAO getOrdineDAO() {
		return new OrdineDaoJDBC(dataSource);
	}
	
	public void updateOrder(Ordine ordine) {
		getOrdineDAO().update(ordine);
	}

	// Menu //
	public Menu findMenuByPrimaryKey(int menuid) {
		return getMenuDAO().findByPrimaryKey(menuid);
	}

	public List<Menu> getAllMenus() {
		return getMenuDAO().findAll();
	}

	public void addMenu(Menu menu) {
		getMenuDAO().save(menu);
	}
	
	public void updateMenu(Menu menu) {
		getMenuDAO().update(menu);
	}

	public void deleteMenu(Menu menu) {
		getMenuDAO().delete(menu);
	}

	public MenuDAO getMenuDAO() {
		return new MenuDaoJDBC(dataSource);
	}
	

	// Prodotti //
	public Prodotto findProdByPrimaryKey(int prodid) {
		return getProdottoDAO().findByPrimaryKey(prodid);
	}

	public List<Prodotto> getAllProds() {
		return getProdottoDAO().findAll();
	}

	public void addProd(Prodotto prod) {
		getProdottoDAO().save(prod);
	}

	public void deleteProd(Prodotto prod) {
		getProdottoDAO().delete(prod);
	}

	public ProdottoDAO getProdottoDAO() {
		return new ProdottoDaoJDBC(dataSource);
	}

}
