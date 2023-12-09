package by.training.home_library.dao;

import by.training.home_library.bean.Account;

public interface AccountDAO {

	public boolean registration(String name, String login, String password) throws DAOException;
	public Account authorization(String login, String password) throws DAOException;
}
