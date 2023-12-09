package by.training.home_library.service;

import by.training.home_library.bean.Account;
import by.training.home_library.dao.DAOException;

public interface AccountService {
	
	public boolean registration(String name, String login, String password) throws ServiceException;
	public Account authorization(String login, String password) throws ServiceException;

}
