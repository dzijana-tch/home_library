package by.training.home_library.service;

import by.training.home_library.service.impl.AccountServiceImpl;
import by.training.home_library.service.impl.BookServiceImpl;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();

	private final BookService bookService = new BookServiceImpl();
	private final AccountService accountService = new AccountServiceImpl();
	
	private ServiceProvider() {	}
	
	public static ServiceProvider getInstance() {
		return instance;
	}

	public BookService getBookService() {
		return bookService;
	}

	public AccountService getAccountService() {
		return accountService;
	}
	
	

}
