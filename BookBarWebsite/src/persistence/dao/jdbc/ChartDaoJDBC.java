package persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Chart;
import persistence.DataSource;
import persistence.dao.ChartDAO;

public class ChartDaoJDBC implements ChartDAO {

	private DataSource dataSource;

	public ChartDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	 /* SELECT public."Product"."Name", public."Product"."Description", public."Chart"."Quantity" FROM public."Chart"
	   JOIN public."User" on public."User"."UserID" = public."Chart"."UserID"
       JOIN public."Product" on public."Product"."ProductID" = public."Chart"."ProductID"
       WHERE public."User"."UserID"=6
	 */

	@Override
	public void save(Chart chart) {
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			String insert = "INSERT INTO public.\"chart\" (\"UserID\", \"ProductID\", \"Quantity\") VALUES (?,?,?);";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, chart.getUtente().getId());
			statement.setInt(2, chart.getProdotto().getId());
			statement.setInt(3, chart.getQuantita());
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

	@Override
	public Chart findByPrimaryKey(int userid, int productid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chart> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Chart chart) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Chart chart) {
		// TODO Auto-generated method stub

	}

}
