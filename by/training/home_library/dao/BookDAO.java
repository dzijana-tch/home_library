package by.training.home_library.dao;

import java.util.Set;

import by.training.home_library.bean.Book;

public interface BookDAO {
	
	public Set<Book> getAll() throws DAOException;
	public Set<Book> search(String word) throws DAOException;
	public boolean add(Book book) throws DAOException; 
	public boolean remove(Book book) throws DAOException;
}
