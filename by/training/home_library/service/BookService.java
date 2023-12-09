package by.training.home_library.service;

import java.util.Set;
import by.training.home_library.bean.Book;
import by.training.home_library.dao.DAOException;

public interface BookService {
	
	public Set<Book> getAll() throws ServiceException;
	public Set<Book> search(String words) throws ServiceException;
	public boolean add(Book book) throws ServiceException; 
	public boolean remove(Book book) throws ServiceException;
	public boolean remove(int numb) throws ServiceException;

}
