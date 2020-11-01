package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.CategoryDao;

public class Menu {

	private CategoryDao categoryDao = new CategoryDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> menuOptions = Arrays.asList("Display Budget Categories", "Create New Category",
			"Delete A Category", "Update Category Balance", "Exit");

	public void start() {

		String selection = "";

		do {
			printMenu();
			selection = scanner.nextLine();

			try {
				if (selection.equals("1")) {
					displayCategories();
				} else if (selection.equals("2")) {
					createCategory();
				} else if (selection.equals("3")) {
					deleteCategory();
				} else if (selection.equals("4")) {
					updateBalance();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Press enter to continue");
			scanner.nextLine();
		} while (!selection.equals("5"));

	}

	private void printMenu() {
		System.out.println("Please select an option: \n-------------------------------------");
		for (int i = 0; i < menuOptions.size(); i++) {
			System.out.println(i + 1 + ") " + menuOptions.get(i));
		}
	}

	private void displayCategories() throws SQLException {
		categoryDao.getCategories();
	}

	private void createCategory() throws SQLException {
		System.out.println("Enter new category Id #: ");
		int categoryId = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter new category name: ");
		String categoryName = scanner.nextLine();
		System.out.println("Enter new category balance: ");
		String balance = scanner.nextLine();
		categoryDao.createNewCategory(categoryId, categoryName, balance);

	}

	private void deleteCategory() throws SQLException {
		System.out.println("Enter ID for category want to delete: ");
		int categoryId = Integer.parseInt(scanner.nextLine());
		categoryDao.deleteCategoryById(categoryId);
	}

	private void updateBalance() throws SQLException {
		System.out.println("Enter ID for category to update balance: ");
		int categoryId = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter new balance: ");
		String balance = scanner.nextLine();
		categoryDao.updateBalanceById(categoryId, balance);
	}
}
