package by.training.home_library.dao.impl;

import java.util.Comparator;
import by.training.home_library.bean.Book;

public class BookCompare implements Comparator<Book> {

	@Override
	public int compare(Book o1, Book o2) {

		int byAuthor = o1.getAuthor().compareTo(o2.getAuthor());
		if (byAuthor != 0) {
			return byAuthor;	
			
		} else {
			int byTitle = o1.getTitle().compareTo(o2.getTitle());
			if (byTitle != 0) {
				return byTitle;
				
			} else {
				int byYear = o1.getYear() - o2.getYear();
				if (byYear != 0) {
					return byYear;
					
				} else {
					if (o1.isPaperBook() != o2.isPaperBook()) {
						if (o1.isPaperBook() == true) {
							return -1;
						} else {
							return 1;
						}
						
					} else {
						return 0;
					}	
				}
			}
		}
	}
}
