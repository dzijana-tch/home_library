 package by.training.home_library.view;

import by.training.home_library.controller.*;
import by.training.home_library.controller.impl.*;

public class MainViewer {

	private ConsoleInput consoleInput = ConsoleInput.getInstance();
	
	private final ControllerProvider provider = ControllerProvider.getInstance();
	private AccountController accountController = provider.getAccountController();
	private BookController bookController = provider.getBookController();
	
	private String login;
	private String password;
	private String name;
	private String role;

	public void launch() {

		welcome();

		loginMenu();
		
		welcome();
		
		switch (role) {
		case "admin":
			adminBookMenu();
			break;
			
		case "user":
			userBookMenu();
			break;
		}
		
	}
	
	private void welcome() {
		
		System.out.println(
				 " _______________________________________________________________________________________________________________\n"
				+ "|														|\n"
				+ "|						ДОМАШНЯЯ БИБЛИОТЕКА						|\n"
				+ "|_______________________________________________________________________________________________________________|\n");
	}
	
	private void loginMenu() {
		
		int choice;
		choice = selectRegOrAuth();

		switch (choice) {
		case 1:
			registration();
			break;
			
		case 2:
			authorization();
			break;
		}
		
		

	}
	
	private int selectRegOrAuth() {
		System.out.println("Введите:\n"
						+ "1 - если хотите зарегистрироваться\n"
						+ "2 - если хотите авторизоваться");
		
		int choice;
		choice = consoleInput.getInt(">>");

		while (choice > 2 || choice < 1) {
			System.out.println("Неверный ввод. Выберите 1 или 2\n"
							+ "1 - если хотите зарегистрироваться\n"
							+ "2 - если хотите авторизоваться");
			choice = consoleInput.getInt(">>");
		}
		return choice;
	}
	
	private void registration() {
		System.out.println("\n							Регистрация");
		System.out.println("Введите адрес электронной почты в качестве логина.");
		login = consoleInput.getString(">>");

		System.out.println("Введите пароль. Допускается 8-16 символов – латин.буквы, араб.цифры, спец.символы.  минимум 1 буква и минимум 1 цифра.");
		password = consoleInput.getString(">>");
		System.out.println("Повторите пароль");
		String repeat = consoleInput.getString(">>");

		while (!password.equals(repeat)) {
			System.out.println("Пароль не совпадает. Повторите ввод");
			System.out.println("Введите пароль");
			password = consoleInput.getString(">>");
			System.out.println("Повторите пароль");
			repeat = consoleInput.getString(">>");
		}

		System.out.println("Как к вам можно обращаться?");
		name = consoleInput.getString(">>");
		
		String response;
		response = accountController.doAction("registration name=" + name + " login=" + login + " password=" + password);
		
		//String result;
		//result = response.split("\\s")[0];
		
		switch (response) {
		case "1 Account is already registered":
			System.out.println("Пользователь с таким логином уже существует.");
			loginMenu();
			break;
		case "0 Account registered":
			System.out.println("Регистрация прошла успешно! Теперь авторизуйтесь.");
			authorization();
			break;
		case "1 Invalid password":
			System.out.println("Пароль не соответствует требованиям. Повторите регистрацию, используя корректный пароль.");
			registration();
			break;
		case "1 Invalid login":
			System.out.println("Логин не соответствует требованиям. Повторите регистрацию, используя корректный адрес электронной почты.");
			registration();
			break;
		case "1 Empty field":
			System.out.println("Не все поля заполнены. Повторите регистрацию, заполнив все поля.");
			registration();
			break;
		}
	}

	private void authorization() {
		System.out.println("\n							Авторизация");
		System.out.println("Введите логин");
		login = consoleInput.getString(">>");
		System.out.println("Введите пароль");
		password = consoleInput.getString(">>");
		
		String response;
		response = accountController.doAction("authorization login=" + login + " password=" + password);
		
		String result;
		result = response.split("\\s")[0];
		
		switch (result) {
		case "1":
			System.out.println("Неверный логин или пароль.");
			loginMenu();
			break;
		case "0":
			//"0 Login role=admin name="Diana
			String[] params = response.split("\\s");
			role = params[2].split("=")[1];
			name = params[3].split("=")[1];	
			
			System.out.println("Добро пожаловать, " + name + "!");
			break;
		}
	}

	private void adminBookMenu() {

		int choice;
		choice = selectActionForAdmin();

		switch (choice) {
		case 1:
			getAll();
			break;
			
		case 2:
			search();
			break;
			
		case 3:
			add();
			break;
			
		case 4:
			remove();
			break;
		}
		adminBookMenu();
	}
	
	private int selectActionForAdmin() {
		System.out.println("Выберите действие:\n"
				+ "1 - посмотреть все книги\n"
				+ "2 - поиск\n"
				+ "3 - добавить книгу\n"
				+ "4 - удалить книгу");
		
		int choice;
		choice = consoleInput.getInt(">>");

		while (choice > 4 || choice < 1) {
			System.out.println("Неверный ввод. Выберите 1, 2, 3 или 4:\n"
					+ "1 - посмотреть все книги\n"
					+ "2 - поиск\n"
					+ "3 - добавить книгу\n"
					+ "4 - удалить книгу");
			choice = consoleInput.getInt(">>");
		}
		return choice;
	}
	
	private void getAll() {
		System.out.println("							Все книги");
		
		String response;
		response = bookController.doAction("getAll");
		
		String result;
		result = response.substring(0, 1);
		
		switch (result) {
		case "1":
			System.out.println("Книг не найдено");
			break;
		case "0":
			System.out.println(response.substring(2));
			break;
		}		
	}

	private void search() {
		String keyword;
		
		System.out.println("Введите ключевые слова");
		keyword = consoleInput.getString(">>");
		
		String response;
		response = bookController.doAction("search keyword=" + keyword);
		
		String[] params;
		String result;
		
		params = response.split("\\s", 2);
		result = params[0];
		
		switch (result) {
		case "1":
			System.out.println("Не найдено книг по ключевым словам " + keyword);
			break;
		case "0":
			System.out.println("Результаты поиска:\n" + params[1]);
			break;
		}		
	}
	
	private void add() {
		System.out.println("				Форма для добавления книги");
		
		boolean isPaperBook = false;
		String author;
		String title;
		int year;
		String description;
		
		System.out.println("Введите:\n"
						+ "1 - если книга в бумажном варианте\n"
						+ "2 - если книга в электронном виде");
		int choice;
		choice = consoleInput.getInt(">>");
		while (choice > 2 || choice < 1) {
			System.out.println("Неверный ввод. Введите 1 или 2:\n"
					+ "1 - если книга в бумажном варианте\n"
					+ "2 - если книга в электронном виде");
			choice = consoleInput.getInt(">>");
		}
		
		switch (choice) {
		case 1:
			isPaperBook = true;
			break;
		case 2:
			isPaperBook = false;
			break;
		}

		System.out.println("Введите автора(ов)");
		author = consoleInput.getString(">>");
		
		System.out.println("Введите название");
		title = consoleInput.getString(">>");
		
		System.out.println("Введите год издания");
		year = consoleInput.getInt(">>");
		
		System.out.println("Введите описание");
		description = consoleInput.getString(">>");
		
		String response;
		response = bookController.doAction("add isPaperBook=" + isPaperBook + " author=" + author + " title=" + title 
											+ " year=" + year + " description=" + description);
		String[] params;
		String result;
		
		params = response.split("\\s", 2);
		result = params[0];
		
		switch (result) {
		case "0":
			System.out.println("Успешно добавлена книга:\n" + params[1]);
			break;
		case "1":
			System.out.println("Данная книга уже существует.");
			break;
		}
		
	}

	private void remove() {
		getAll();
		System.out.println("Введите номер книги для удаления:");
		int numb = consoleInput.getInt(">>");
		
		String response;
		response = bookController.doAction("remove number=" + numb);
		
		String[] params;
		String result;
		
		params = response.split("\\s", 2);
		result = params[0];
		
		switch (result) {
		case "0":
			System.out.println("Книга удалена.");
			break;
		case "1":
			System.out.println("Такая книга не найдена.");
			break;
		}
	}

	private void userBookMenu() {

		int choice;
		choice = selectActionForUser();

		switch (choice) {
		case 1:
			getAll();
			break;
			
		case 2:
			search();
			break;
		}
			
		userBookMenu();
	}
	
	private int selectActionForUser() {
		System.out.println("Выберите действие:\n"
				+ "1 - посмотреть все книги\n"
				+ "2 - поиск");
		
		int choice;
		choice = consoleInput.getInt(">>");

		while (choice > 2 || choice < 1) {
			System.out.println("Неверный ввод. Выберите 1, 2, 3 или 4:\n"
					+ "1 - посмотреть все книги\n"
					+ "2 - поиск");
			choice = consoleInput.getInt(">>");
		}
		return choice;
	}

}
