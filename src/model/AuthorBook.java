package model;

public class AuthorBook {
	private AuthorModel author;
	private BookModel book;
	private int royalty;
	private boolean newRecord;
	
	public AuthorBook() {
		author = new AuthorModel();
		book = new BookModel();
		newRecord = true;
	}
	
	public AuthorModel getAuthor() {
		return author;
	}

	public void setAuthor(AuthorModel author) {
		this.author = author;
	}

	public BookModel getBook() {
		return book;
	}

	public void setBook(BookModel book) {
		this.book = book;
	}

	public int getRoyalty() {
		return royalty;
	}
	
	public void setRoyalty(double royalty) {
		if(royalty < 0.0) {
			this.royalty = 0 * 100000;
		}else if(royalty > 1.0) {
			royalty = 1.0 * 100000;
		}else {
			this.royalty = (int) (royalty * 100000);	
		}
	}
	
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
	
	public boolean getNewRecord() {
		return newRecord;
	}
}
