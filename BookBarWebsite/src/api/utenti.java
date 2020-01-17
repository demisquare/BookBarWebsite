package api;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Utente;
import persistence.DBManager;

@SuppressWarnings("serial")
public final class utenti extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    DBManager dbm = persistence.DBManager.getInstance();
    List<Utente> utenti = new LinkedList<>();
    utenti = dbm.getAllUsers();

    String json = new Gson().toJson(utenti);

    res.setContentType("application/json");
    res.setCharacterEncoding("UTF-8");
    res.getWriter().write(json);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {}
}