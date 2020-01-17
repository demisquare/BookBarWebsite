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

  public UtenteDaoJDBC(DataSource dataSource) { this.dataSource = dataSource; }

  public void save(Utente utente) {
    Connection connection = null;
    try {
      connection = this.dataSource.getConnection();
      String insert = "INSERT INTO public.users (username, password, role) VALUES (?,?,?);";
      PreparedStatement statement = connection.prepareStatement(insert);
      statement.setString(1, utente.getUsername());
      statement.setString(2, utente.getPassword());
      statement.setString(3, utente.getRole());
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
      String query = "select * from users where id = ?";
      statement = connection.prepareStatement(query);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();

      if (result.next()) {
        utente = new Utente();
        utente.setId(result.getString("id"));
        utente.setUsername(result.getString("username"));
        utente.setRole(result.getString("role"));
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
      System.out.println(
          "  -------> Connected to the PostgreSQL server successfully.");
      Utente utente;
      PreparedStatement statement;
      String query = "select * from public.users";
      statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();
      while (result.next()) {
        utente = new Utente();
        utente.setUsername(result.getString("username"));
        utente.setId(result.getString("id"));
        utente.setRole(result.getString("role"));
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
      String update =
          "update users SET username = ?, password = ?, role = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(update);
      statement.setString(1, utente.getUsername());
      statement.setString(2, utente.getPassword());
      statement.setString(3, utente.getRole());
      statement.setInt(4, utente.getId());
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
      String delete = "delete FROM users WHERE id = ?";
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
