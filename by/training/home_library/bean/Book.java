package by.training.home_library.bean;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 4788468102579427711L;
	
	private boolean isPaperBook;
	private String author;
	private String title;
	private int year;
	private String description;
	
	public Book() {}
	
	public Book(boolean isPaperBook, String author, String title, int year, String description) {
		super();
		this.isPaperBook = isPaperBook;
		this.author = author;
		this.title = title;
		this.year = year;
		this.description = description;
	}

	public boolean isPaperBook() {
		return isPaperBook;
	}

	public void setPaperBook(boolean isPaperBook) {
		this.isPaperBook = isPaperBook;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, description, isPaperBook, title, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& isPaperBook == other.isPaperBook && Objects.equals(title, other.title) && year == other.year;
	}

	@Override
	public String toString() {
		return "Book [isPaperBook=" + isPaperBook + ", author=" + author + ", title=" + title + ", year=" + year
				+ ", description=" + description + "]";
	}
	
}
