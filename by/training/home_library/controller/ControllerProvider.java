package by.training.home_library.controller;

import by.training.home_library.controller.impl.AccountController;
import by.training.home_library.controller.impl.BookController;

public class ControllerProvider {
	
	private static final ControllerProvider instance = new ControllerProvider();

	private final BookController bookController = new BookController();
	private final AccountController accountController = new AccountController();
	
	private ControllerProvider() {	}
	
	public static ControllerProvider getInstance() {
		return instance;
	}

	public BookController getBookController() {
		return bookController;
	}

	public AccountController getAccountController() {
		return accountController;
	}


}