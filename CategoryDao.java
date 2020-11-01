package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao {

	private Connection connection;
	private final String GET_CATEGORIES_QUERY = "SELECT * FROM categories";
	private final String CREATE_NEW_CATEGORY_QUERY = "INSERT into categories (id, name, balance) values (?,?,?)";
	private final String DELETE_CATEGORY_BY_ID = "DELETE from categories WHERE id = ?";
	private final String UPDATE_BALANCE_BY_ID = "UPDATE categories SET balance = ? WHERE id = ?";

	public CategoryDao() {
		connection = DBConnection.getConnection();
	}

	public void getCategories() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CATEGORIES_QUERY).executeQuery();

		while (rs.next()) {
			System.out.println("Category ID:" + rs.getInt("id") + "| Category: " + rs.getString("name") + "| Balance: $"
					+ rs.getString("balance"));
		}
	}

	public void createNewCategory(int categoryId, String categoryName, String balance) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CATEGORY_QUERY);
		ps.setInt(1, categoryId);
		ps.setString(2, categoryName);
		ps.setString(3, balance);
		ps.executeUpdate();
	}

	public void deleteCategoryById(int categoryId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORY_BY_ID);
		ps.setInt(1, categoryId);
		ps.executeUpdate();
	}

	public void updateBalanceById(int categoryId, String balance) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_BALANCE_BY_ID);
		ps.setInt(2, categoryId);
		ps.setString(1, balance);
		ps.executeUpdate();

	}
}
