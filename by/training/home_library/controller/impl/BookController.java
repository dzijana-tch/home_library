package by.training.home_library.controller.impl;

import java.util.Set;

import by.training.home_library.bean.Book;
import by.training.home_library.controller.Controller;
import by.training.home_library.service.BookService;
import by.training.home_library.service.ServiceException;
import by.training.home_library.service.ServiceProvider;
import by.training.home_library.view.BookViewer;

public class BookController implements Controller {
	
	private final ServiceProvider provider = ServiceProvider.getInstance();
	private BookService bookService = provider.getBookService();
	
	@Override
	public String doAction(String request) {
		
		String response = "";
		
		String[] params;
		String commandName;
		
		params = request.split("\\s+?\\w+?=");
		commandName = params[0];
		
		switch (commandName) {
		case "getAll":
			
			Set<Book> books = null;
			
			try {
				books = bookService.getAll();
			} catch (ServiceException e) {
				System.out.println("Упс! Произошла ошибка. Обратитеcь к администратору.");
			}
			
			if (books == null || books.size() == 0) {
				response = "1 Empty list";
			} else {
				response = "0 " + BookViewer.parseString(books);
			}
			
			break;
			
		case "search":
			
			Set<Book> foundBooks = null;
			String keyword;
			keyword = params[1];
			
			try {
				foundBooks = bookService.search(keyword);
			} catch (ServiceException e) {
				System.out.println("Упс! Произошла ошибка. Обратитеcь к администратору.");
			}
			
			if (foundBooks == null || foundBooks.size() == 0) {
				response = "1 Empty list";
			} else {
				response = "0 " + BookViewer.parseString(foundBooks);
			}
			break;
			
		case "add":
			
			boolean isPaperBook;
			String author;
			String title;
			int year;
			String description;
			
			if (params[1].equals("true")) {
				isPaperBook = true;
			} else {
				isPaperBook = false;
			}			
			
			author = params[2];
			title = params[3];
			year = Integer.parseInt(params[4]);
			description = params[5];
			
			boolean result = false;
			Book book = new Book(isPaperBook, author, title, year, description);
			try {
				result = bookService.add(book);
			} catch (ServiceException e) {
				System.out.println("Упс! Произошла ошибка. Обратитеcь к администратору.");
			}
			
			if (result) {
				response = "0 " + BookViewer.parseString(book);
			} else {
				response = "1 The book is already exists";
			}
			
			break;
			
		case "remove":
			
			String sNumb;
			int numb;
			boolean removed;
			
			sNumb = params[1];
			numb = Integer.parseInt(sNumb);
			removed = false;
			
			try {
				if (numb < 1 || numb > bookService.getAll().size()) {
					return "1 No such object";
				}
				
				removed = bookService.remove(numb);
				
				if (removed) {
					response = "0 The book is removed";
				} else {
					response = "1 No such object";
				}
			
			} catch (ServiceException e) {
				System.out.println("Упс! Произошла ошибка. Обратитеcь к администратору.");
			}
			
			break;
		}
		
		return response;
	}
	
	

}
