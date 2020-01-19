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

  public OrdineDaoJDBC(DataSource dataSource) { this.dataSource = dataSource; }

  public void save(Ordine ordine) {
    // Connection connection = null;
    // try {
    // connection = this.dataSource.getConnection();
    // String insert =
    // "insert into ordine(matricola, nome, cognome,"
    // + " datanascita, scuola, corsodilaurea) values (?,?,?,?,?,?)";
    // PreparedStatement statement = connection.prepareStatement(insert);
    // statement.setString(1, ordine.getMatricola());
    // statement.setString(2, ordine.getNome());
    // statement.setString(3, ordine.getCognome());
    // statement.setString(4, ordine.getDataNascita());
    // statement.setLong(5, ordine.getScuolaDiDiploma().getId());
    // // statement.setLong(5,
    // // ordine.get.getScuolaDiDiploma().getId());
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

  public Ordine findByPrimaryKey(String userid, String menuid) {
    Connection connection = null;
    Ordine ordine = null;
    try {
      connection = this.dataSource.getConnection();
      PreparedStatement statement;
      String query =
          "SELECT * FROM public.\"Order\" WHERE public.\"Order\".\"UserID\" = ? AND public.\"Order\".\"MenuID\" = ?";
      statement = connection.prepareStatement(query);
      statement.setString(1, userid);
      statement.setString(1, menuid);

      ResultSet result = statement.executeQuery();
      if (result.next()) {
        //        ordine = new Ordine();
        //        ordine.setMatricola(result.getString("UserID"));
        //        ordine.setNome(result.getString("MenuID"));
        //
        //        Utente utente =
        //        DBManager.getInstance().getUtenteDAO().findByPrimaryKey(
        //            result.getInt("UserID"));
        //				ordine.setUser(utente);
        //
        //        Menu menu =
        //        DBManager.getInstance().getMenuDAO().findByPrimaryKey(
        //            result.getInt("MenuID"));
        //        ordine.setMenu(menu);
        //
        //				ordine.setSatus(result.getString("Status"));
        //        ordine.setData(result.getString("Data"));
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
      String query = "SELECT * FROM public.\"Order\"";
      statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();
      while (result.next()) {
        ordine = new Ordine();
        Utente user = DBManager.getInstance().getUtenteDAO().findByPrimaryKey(
            result.getInt("UserID"));
        ordine.setUser(user);

        Menu menu = DBManager.getInstance().getMenuDAO().findByPrimaryKey(result.getInt("MenuID"));
        ordine.setMenu(menu);

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
    // Connection connection = null;
    // try {
    // connection = this.dataSource.getConnection();
    // String update =
    // "UPDATE public.\"Order\" SET public.\"Order\".\"Status\" = ? WHERE
    // public.\"Order\".\"UserID\" = ? AND public.\"Order\".\"MenuID\" = ? ";
    // PreparedStatement statement = connection.prepareStatement(update);
    // statement.setString(1, ordine.getStatus());
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
