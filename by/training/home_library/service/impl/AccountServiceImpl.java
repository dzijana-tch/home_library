package by.training.home_library.service.impl;

import by.training.home_library.bean.Account;
import by.training.home_library.dao.AccountDAO;
import by.training.home_library.dao.DAOException;
import by.training.home_library.dao.DAOProvider;
import by.training.home_library.service.AccountService;
import by.training.home_library.service.Encoder;
import by.training.home_library.service.ServiceException;

public class AccountServiceImpl implements AccountService {
	
	private final DAOProvider provider = DAOProvider.getInstance();
	private AccountDAO accountDAO = provider.getAccountDAO();
	
	@Override
	public boolean registration(String name, String login, String password) throws ServiceException {
		
		Encoder encoder;
		String encryptedPassword;
		
		encoder = new Encoder();
		encryptedPassword = encoder.encrypt(password);
		
		try {
			return accountDAO.registration(name, login, encryptedPassword);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	
	@Override
	public Account authorization(String login, String password) throws ServiceException {
		
		Encoder encoder;
		String encryptedPassword;
		
		encoder = new Encoder();
		encryptedPassword = encoder.encrypt(password);

		try {
			return accountDAO.authorization(login, encryptedPassword);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}


	
}
