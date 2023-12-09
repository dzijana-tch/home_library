package by.training.home_library.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import by.training.home_library.bean.Book;
import by.training.home_library.dao.BookDAO;
import by.training.home_library.dao.DAOException;

public class FileBookDAO implements BookDAO {

	public static final String SOURCE = "e:\\Diana\\eclipse-workspace\\home-library\\src\\by\\training\\home_library\\resources\\ListOfBooks.txt";

	@Override
	public Set<Book> getAll() throws DAOException {

		BookCompare bookCompare = new BookCompare();
		Set<Book> allBooks = new TreeSet<Book>(bookCompare);

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(SOURCE))) {

			String line;
			String[] lines = new String[5];
			Book book;

			while ((line = bufferedReader.readLine()) != null) {
				lines[0] = line;
				for (int i = 1; i < 5; i++) {
					lines[i] = bufferedReader.readLine();
				}
				bufferedReader.readLine();

				book = makeBook(lines);
				allBooks.add(book);
			}

		} catch (IOException ex) {
			throw new DAOException(ex);
		}

		return allBooks;
	}

	private Book makeBook(String[] lines) {
		Book book;

		String trueOrFalse;
		boolean isPaperBook;
		String author;
		String title;
		String sYear;
		int year;
		String description;

		trueOrFalse = lines[0].split("=")[1];
		if (trueOrFalse.equals("true")) {
			isPaperBook = true;
		} else {
			isPaperBook = false;
		}

		author = lines[1].split("=")[1];

		title = lines[2].split("=")[1];

		sYear = lines[3].split("=")[1];
		year = Integer.parseInt(sYear);

		description = lines[4].split("=")[1];

		book = new Book(isPaperBook, author, title, year, description);

		return book;
	}

	@Override
	public Set<Book> search(String word) throws DAOException {
		Set<Book> all = getAll();
		BookCompare bookCompare = new BookCompare();
		Set<Book> found = new TreeSet<Book>(bookCompare);

		word = word.toLowerCase();
		for (Book book : all) {
			if (book.getAuthor().toLowerCase().contains(word) || book.getTitle().toLowerCase().contains(word)
					|| book.getDescription().toLowerCase().contains(word)) {
				found.add(book);
			}
		}

		return found;
	}

	@Override
	public boolean add(Book book) throws DAOException {

		if (search(book)) {
			return false;
		}

		String string = parse(book);

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(SOURCE, true))) {
			bufferedWriter.write(string);

		} catch (IOException ex) {
			throw new DAOException(ex);
		}

		return true;
	}

	private boolean search(Book book) throws DAOException {

		Set<Book> all = getAll();
		BookCompare bookCompare = new BookCompare();

		for (Book b : all) {
			if (bookCompare.compare(b, book) == 0) {
				return true;
			}
		}

		return false;
	}

	private String parse(Book book) {

		String string = "";

		string += "isPaperBook=" + book.isPaperBook() + "\n";
		string += "author=" + book.getAuthor() + "\n";
		string += "title=" + book.getTitle() + "\n";
		string += "year=" + book.getYear() + "\n";
		string += "description=" + book.getDescription() + "\n";
		string += "--------------------------------------------------\n";

		return string;
	}

	@Override
	public boolean remove(Book book) throws DAOException {

		Set<Book> all = getAll();
		BookCompare bookCompare = new BookCompare();

		for (Book b : all) {
			if (bookCompare.compare(b, book) == 0) {
				all.remove(b);

				File bookList = new File(SOURCE);
				bookList.delete();
				File newBookList = new File(SOURCE);

				try {
					newBookList.createNewFile();
				} catch (IOException e) {
					throw new DAOException();
				}

				for (Book n : all) {
					add(n);
				}

				return true;
			}
		}

		return false;
	}

}
