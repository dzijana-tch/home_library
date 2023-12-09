package by.training.home_library.view;

import java.util.Set;

import by.training.home_library.bean.Book;

public class BookViewer {
	
	public static String parseString(Book book) {
		String string = "";
		
		string += book.getAuthor() + "\n";
		string += "\"" + book.getTitle() + "\", " + book.getYear() + "\n";
		if (book.isPaperBook()) {
			string += "В бумажном виде\n";
		} else {
			string += "В электронном виде\n";
		}
		string += book.getDescription() + "\n";
		
		return string;
	}
	
	public static String parseString(Set<Book> books) {
		String string = "";
		int i = 1;
		
		for (Book book : books) {
			string += i + ". " + BookViewer.parseString(book) + "\n";
			i++;
		}
		return string;
	}

}
