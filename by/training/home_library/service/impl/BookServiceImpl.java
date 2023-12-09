package by.training.home_library.service.impl;

import java.util.Set;
import java.util.TreeSet;

import by.training.home_library.bean.Book;
import by.training.home_library.dao.BookDAO;
import by.training.home_library.dao.DAOException;
import by.training.home_library.dao.DAOProvider;
import by.training.home_library.dao.impl.BookCompare;
import by.training.home_library.service.BookService;
import by.training.home_library.service.ServiceException;

public class BookServiceImpl implements BookService {
	
	private final DAOProvider provider = DAOProvider.getInstance();	
	private BookDAO bookDAO = provider.getBookDAO();
	
	@Override
	public Set<Book> getAll() throws ServiceException {
		
		try {
			return bookDAO.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	@Override
	public Set<Book> search(String words) throws ServiceException {

		String[] keywords = words.split("\\s");
		BookCompare bookCompare = new BookCompare();
		Set<Book> foundBooks = new TreeSet<Book>(bookCompare);
		
		try {
			for (String keyword : keywords) {
				foundBooks.addAll(bookDAO.search(keyword));
			}			
			return foundBooks;
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	@Override
	public boolean add(Book book) throws ServiceException {

		try {
			return bookDAO.add(book);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	@Override
	public boolean remove(Book book) throws ServiceException {
		
		try {
			return bookDAO.remove(book);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public boolean remove(int numb) throws ServiceException {
		
		Set<Book> allBooks;
		Book book;
		
		allBooks = getAll();
		book = null;
		
		int i = 1;
		for (Book b : allBooks) {
			if (i == numb) {
				book = b;
			}
			i++;
		}
		return remove(book);
	}

}
