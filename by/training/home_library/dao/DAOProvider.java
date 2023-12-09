package by.training.home_library.dao;

import by.training.home_library.dao.impl.FileAccountDAO;
import by.training.home_library.dao.impl.FileBookDAO;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();

	private final BookDAO bookDAO = new FileBookDAO();
	private final AccountDAO accountDAO = new FileAccountDAO();
	
	private DAOProvider() {	}
	
	public static DAOProvider getInstance() {
		return instance;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

}
