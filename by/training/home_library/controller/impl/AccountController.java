package by.training.home_library.controller.impl;

import java.util.regex.Pattern;

import by.training.home_library.bean.Account;
import by.training.home_library.bean.Role;
import by.training.home_library.controller.*;
import by.training.home_library.service.*;

public class AccountController implements Controller {
	
	// Требования к логину: _____@___.__
	// Требования к паролю: 8-16 символов; лат.буквы, араб.цифры, спец.символы; мин.1 буква и мин.1 цифра.
		
	
	private final ServiceProvider provider = ServiceProvider.getInstance();
	private AccountService accountService = provider.getAccountService();
	
	@Override
	public String doAction(String request) {
		
		String[] params;
		String commandName;
		
		params = request.split("\\s");
		commandName = params[0];
		
		switch (commandName) {
		case "registration":
			String name;
			String login;
			String password;
			
			if (params[1].split("=").length < 2 || params[2].split("=").length < 2 || params[3].split("=").length < 2) {
				return "1 Empty field";
			}
			
			name = params[1].split("=")[1];
			login = params[2].split("=")[1];
			password = params[3].split("=")[1];
			
			if (!loginCheck(login)) {
				return "1 Invalid login";
			}
			
			if (!passwordCheck(password)) {
				return "1 Invalid password";
			}
			
			boolean result = false;
			try {
				result = accountService.registration(name, login, password);
			} catch (ServiceException e) {
				System.out.println("Упс! Произошла ошибка. Обратитеcь к администратору.");
			}
			
			if (result) {
				return "0 Account registered";
			} else {
				return "1 Account is already registered";
			}
			
		case "authorization":
			login = params[1].split("=")[1];
			password = params[2].split("=")[1];
			
			Account account = null;
			try {
				account = accountService.authorization(login, password);
			} catch (ServiceException e) {
				System.out.println("Упс! Произошла ошибка. Обратитеcь к администратору.");
			}
			
			if (account.getRole() == Role.NONE) {
				return "1 Invalid input data";
			} else if (account.getRole() == Role.ADMIN) {
				return "0 Login role=admin name=" + account.getName();
			} else if (account.getRole() == Role.USER) {
				return "0 Login role=user name=" + account.getName();
			}
			break;
		}
		return "";
	}

	
	private boolean passwordCheck(String password) {
		
		if (password.length() < 8 || password.length() > 16) {
			return false;
		}
		
		char[] ar = password.toCharArray();
		boolean oneNumber = false;
		boolean oneLetter = false;
		
		for (char c : ar) {
			if (c < 33 || c > 126) {
				return false;
			}
			if (c >= 48 && c <= 57) {
				oneNumber = true;
			}
			if (c >= 65 && c <= 90 || c >= 97 && c <= 122) {
				oneLetter = true;
			}
		}
		
		if (!oneNumber || !oneLetter) {
			return false;
		}
		
		return true;
	}
	
	private boolean loginCheck(String login) {
		
		return Pattern.matches(".+@.+\u002E.+", login);
	}

}
