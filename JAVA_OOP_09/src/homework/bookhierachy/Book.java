package homework.bookhierachy;

import java.util.HashSet;

import java.util.Iterator;
import java.util.Set;
import homework.tax.Taxable;

public class Book extends Product implements Taxable {

	private String author;
	private String title;
	private final int yearOfPublication;
	private int price;
	private int pages;
	private BookStyle style;
	private int taxPercent = 5;
	private int Tax;
	private static int number = 0;

	
	public static int getNumber() {
		return number;
	}

	private static void incrementNumber() {
		number++;
	}

	private static void decrementNumber() {
		number--;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		decrementNumber();
	}

	public void setStyle(String style) {
		if (benneVan(style)) {
			for (BookStyle c : BookStyle.values()) {
				if(c.name().equalsIgnoreCase(style)) {
					this.style = c;
				}
			}
		} 
		else {
			for (BookStyle c : BookStyle.values()) {
				if(c.name().equalsIgnoreCase("OTHER")) {
					this.style = c;
				}
			}
		}
	}

	public Book(String name, int price, int taxPercent, String style) {
		
		this.price = price;
		this.yearOfPublication = year;
		setStyle(style);
		incrementNumber();
	}

	public String getStyle() {
		return style.name();
	}

	private boolean styleIsValid(BookStyle style) {
		for (BookStyle validStyle : BookStyle.values()) {
			if (validStyle == style) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void increasePrice(int percent) {

		if (percent <= 0) {
			return;
		} else {
			double increasePercentage = (double) percent / 100;

			int newPrice = (int) Math.round(price * (1 + increasePercentage));

			price = newPrice;
		}

	}

	java.time.LocalDate currentDate = java.time.LocalDate.now();
	int year = currentDate.getYear();

	public Book(String name, int price) {
		super(name, price);
		this.yearOfPublication = year;
		this.pages = 100;
		
	}

	public Book(String author, String title, int price, int pages, String style) {
		
		this.yearOfPublication = year;
		this.author = author;
		this.title = title;
		this.price = price >= 0 ? price : 0;
		
		if(pages > 0) {
			this.pages = pages;
		}
		if(benneVan(style)) {
			for(BookStyle c : BookStyle.values()) {
				if(c.name().equalsIgnoreCase(style)) {
					this.style = c;
				}
			}
		}
		else {
			for(BookStyle c : BookStyle.values()) {
				if(c.name().equalsIgnoreCase("OTHER")) {
					this.style = c;
				}
			}	
		}
		Book.number++;
	}

	public Book(String author, String title, int price, String style) {
		this.author = author;
		this.title = title;
		this.price = 2500;
		this.pages = pages >= 0 ? pages : 0;
		this.yearOfPublication = year;
	}

	public Book(String author, String title, String style) {
		
		this.yearOfPublication = year;
		this.author = author;
		this.title = title;
		this.price = 2500;
		
		if(pages > 0) {
			this.pages = pages;
		}
		if(benneVan(style)) {
			for(BookStyle c : BookStyle.values()) {
				if(c.name().equalsIgnoreCase(style)) {
					this.style = c;
				}
			}
		}
		else {
			for(BookStyle c : BookStyle.values()) {
				if(c.name().equalsIgnoreCase("OTHER")) {
					this.style = c;
				}
			}	
		}
		this.pages = 100;
		Book.number++;
		

	}
	
	public static boolean benneVan(String test) {
		for(BookStyle c : BookStyle.values()) {
			if(c.name().equalsIgnoreCase(test)) {
				return true;
			}
		}
		return false;
	}

	public static int comparePublicationDate(Book first, Book second) {
		if (first.getYearOfPublication() > second.getYearOfPublication()) {
			return 1;
		} else if (first.getYearOfPublication() < second.getYearOfPublication()) {
			return 2;
		} else {
			return 0;
		}
	}

	@Override
	public void decreasePrice(int percent) {

		switch (this.style) {
		case CHILDREN: {
			super.decreasePrice(percent+7);
			break;
		}
		case GUIDE: {
			super.decreasePrice((percent+5));
			break;
		}
		default:
			super.decreasePrice(percent);	
		}
	}

	public String displayInfo() {
		return author + ": " + title + ", " + yearOfPublication + ". Price: " + price + " Ft" + ", " + "Pages: "
				+ pages;
	}

	@Override
	public String toString() {
		return super.toString() + ", Style: " + style + author + ": " + title + ", " + yearOfPublication + ". Price: "
				+ price + " Ft" + ", " + "Pages: " + pages + "EgysegAr:" + getUnitPrice();
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

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		if (this.pages < 0) {
			this.pages = pages;
		}
	}

	public int getYearOfPublication() {
		return yearOfPublication;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if (price <= 1000)
			this.price = price;
		else
			this.price = price;
	}

	public boolean hasEvenPages() {
		return pages % 2 == 0;
	}

	public static Book getLonger(Book book1, Book book2) {
		return (book1.getPages() >= book2.getPages()) ? book1 : book2;
	}

	public static void listBookArray(Book[] books) {
		for (Book book : books) {
			System.out.println(book);
		}
	}

	public static Book getLongestBook(Book[] books) {
		Book longestBook = books[0];
		for (int i = 1; i < books.length; i++) {
			if (books[i].getPages() > longestBook.getPages()) {
				longestBook = books[i];
			}
		}
		return longestBook;
	}

	public static Book getLongestEvenPagesBook(Book[] books) {
		Book longestEvenPagesBook = null;
		for (Book book : books) {
			if (book.hasEvenPages()) {
				if (longestEvenPagesBook == null || book.getPages() > longestEvenPagesBook.getPages()) {
					longestEvenPagesBook = book;
				}
			}
		}
		return longestEvenPagesBook;
	}

	public static void authorStatistics(Book[] books) {
		System.out.println("\nSzerzői statisztika:");
		for (int i = 0; i < books.length; i++) {
			int count = 1;
			if (books[i] != null) {
				for (int j = i + 1; j < books.length; j++) {
					if (books[j] != null && books[j].getAuthor().equals(books[i].getAuthor())) {
						count++;
						books[j] = null;
					}
				}
				System.out.println(books[i].getAuthor() + ": " + count + " könyv");
			}
		}
	}

	public static int avgPrice(Book[] books, String style) {
		int avg = 0;
		int db = 0;
		BookStyle help = BookStyle.OTHER;
		if(benneVan(style)) {
			for (BookStyle c : BookStyle.values()) {
				if(c.name().equalsIgnoreCase(style)) {
					help = c;
				}
			}
		}
		else {
			return 0;
		}
		for (int i = 0; i < books.length; i++) {
			if(help == books[i].style) {
				db++;
				avg += books[i].getPrice();
			}
		}
		if(db != 0) {
			return avg / db;
		}
		return 0;
	}

	public static int countStyles(Book[] books) {
		BookStyle[] differentNames = new BookStyle[books.length];
		int differentSum = 1;
		for (int i = 0; i < differentNames.length; i++) {
			differentNames[i] = books[0].style;
		}
		for (int i = 0; i < books.length; i++) {
			for (int j = 0; j < differentNames.length; j++) {
				if(differentNames[j] == books[i].style) {
					break;
				}
				else if (j == (differentNames.length-1)) {
					differentNames[differentSum] = books[i].style;
					differentSum++;
				}
			}
		}
		return differentSum;
	}

	public static void discountBooks(Book[] books, String style) {
		BookStyle help = BookStyle.OTHER;
		if(benneVan(style)) {
			for (BookStyle c : BookStyle.values()) {
				if(c.name().equalsIgnoreCase(style)) {
					help = c;
				}
			}
		}
		else {
			return;
		}
		for (int i = 0; i < books.length; i++) {
			if(help == books[i].style) {
				books[i].decreasePrice(10);
			}
		}
	}

	public static int listBooksWithStyle(Book[] books, String style) {
		int db = 0;
		BookStyle help = BookStyle.OTHER;
		if(benneVan(style)) {
			for (BookStyle c : BookStyle.values()) {
				if(c.name().equalsIgnoreCase(style)) {
					help = c;
				}
			}
		}
		else {
			return 0;
		}
		for (int i = 0; i < books.length; i++) {
			if(help == books[i].style) {
				books[i].toString();
				db++;
			}
		}
		return db;
	}

	@Override
	public int getUnitPrice() {
		int unitPrice = getPrice() / getPages();
		return Math.round(unitPrice);
	}

	public static String[] selectAuthors(Book[] books, int unitPrice) {
		String[] expensiveWritersHelp = new String[books.length];
		int help = 1;
		int early= (books.length+1);
		for (int i = 0; i < books.length; i++) {
			if(books[i].getUnitPrice() > unitPrice) {
				early = i;
				break;
			}
		}
		if(early < books.length) {
			for (int j = 0; j < expensiveWritersHelp.length; j++) {
				expensiveWritersHelp[j] = books[early].author;
			}
		}
		else {
			return null;
		}
		for (int i = 0; i < books.length; i++) {
			if(books[i].getUnitPrice() > unitPrice) {
				for (int j = 0; j < expensiveWritersHelp.length; j++) {
					if(expensiveWritersHelp[j].equalsIgnoreCase(books[i].author)) {
						break;
					}
					else if(j == (expensiveWritersHelp.length-1)) {
						expensiveWritersHelp[help] = books[i].author;
						help++;
					}
				}
			}
		}
		
		String[] expensiveWriters = new String[help];
		for (int i = 0; i < expensiveWriters.length; i++) {
			expensiveWriters[i] = expensiveWritersHelp[i];
		}
		return expensiveWriters;
	}

	@Override
	public double getTaxedValue() {
		if (taxPercent > 0) {
			return this.price + Math.round(this.price * (float) taxPercent / 100);
		}
		return this.price + Math.round(this.price * (float) taxPercent / 100);
	}

	public static int sumGrossPrice(Book[] books) {
		int sum = 0;
		for (int i = 0; i < books.length; i++) {
			if (books[i].getTax() == 100) {
				books[i].setTax(100);
			}
			sum += books[i].getTaxedValue();
		}
		return sum;
	}

	public double getTax() {
		return this.taxPercent;
	}

	private static int taxPercentDefault;

	@Override
	public void setTax(double tax) {
		if (tax == 10 || tax == 0 || tax == -1 || tax == 100) {
			if (tax == 10) {
				this.taxPercent = 100;
			} else if (tax == 100) {
				this.taxPercent = 10;
			} else if (tax == 0) {
				this.taxPercent = 270;
			} else {
				this.taxPercent = 270;
			}

		} else {
			if (tax > 0) {
				this.taxPercent = (int) tax;
			} else {

				this.taxPercent = taxPercentDefault;
			}
		}

	}

}
