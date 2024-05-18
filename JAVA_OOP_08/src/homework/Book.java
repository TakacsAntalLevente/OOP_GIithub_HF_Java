package homework;

import java.util.HashSet;
import java.util.Iterator;

import homework.product.Product;

import java.util.Set;
import homework.tax.Taxable;

public   class Book extends Product implements Taxable{
	
	private String author;
	private String title;
	private final int yearOfPublication;
	private int price;
	private int pages;
	private String style;
	private int taxPercent = 5; 
	private int Tax;

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

		this.author = author;
		this.title = title;
		this.yearOfPublication = year;
		this.price = price >= 0 ? price : 0;
		this.pages = pages >= 0 ? pages : 0;
		this.style = style;
	}

	public Book(String author, String title, int price, String style) {
		this.author = author;
		this.title = title;
		this.price = 2500;
		this.pages = pages >= 0 ? pages : 0;
		this.yearOfPublication = year;
	}

	public Book(String author, String title, String style) {

		this.author = author;
		this.title = title;
		this.price = 2500;
		this.pages = 100;
		this.yearOfPublication = year;

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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public void decreasePrice(int percent) {

		double decreasePercentage = (double) percent / 100;

		if (style.equalsIgnoreCase("children")) {
			decreasePercentage += 0.07;
		} else if (style.equalsIgnoreCase("guide")) {
			decreasePercentage += 0.05;
		}

		int newPrice = (int) Math.round(price * (1 - decreasePercentage));

		if (newPrice >= 0) {
			price = newPrice;
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
		if (price >= 1000)
			this.price = price;
		else
			this.price = 1000;
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
		int count = 0;
		double sum = 0;
		for (Book book : books) {
			if (book != null && book.getStyle().equalsIgnoreCase(style)) {
				sum += book.getPrice();
				count++;
			}
		}
		return count > 0 ? (int) (sum / count) : 0;

	}

	public static int countStyles(Book[] books) {
		Set<String> styleSet = new HashSet<String>();
		for (Book book : books) {
			if (book != null) {
				styleSet.add(book.getStyle().toLowerCase());
			}
		}
		return styleSet.size();
	}

	public static void discountBooks(Book[] books, String style) {
		for (Book book : books) {
			if (book != null && book.getStyle().equalsIgnoreCase(style)) {
				int currentPrice = book.getPrice();
				int discountAmount = (int) (currentPrice * 0.10);
				int newPrice = currentPrice - discountAmount;
				book.setPrice(newPrice);
			}
		}
	}

	public static int listBooksWithStyle(Book[] books, String style) {
		int count = 0;
		for (Book book : books) {
			if (book != null && book.getStyle().equalsIgnoreCase(style)) {
				System.out.println(book);
				count++;
			}
		}
		return count;
	}

	@Override
	public int getUnitPrice() {
		int unitPrice = getPrice() / getPages();
		return Math.round(unitPrice);
	}

	public static String[] selectAuthors(Book[] books, int unitPrice) {
		Set<String> expensiveAuthors = new HashSet<>();

		for (Book book : books) {
			int bookUnitPrice = book.getUnitPrice();

			if (bookUnitPrice > unitPrice) {
				expensiveAuthors.add(book.getAuthor());
			}
		}

		return expensiveAuthors.toArray(new String[0]);
	}
	
	@Override
	public double getTaxedValue() {
		if(taxPercent > 0) {
			return this.price + Math.round(this.price*(float)taxPercent/100);
		}
		return this.price + Math.round(this.price*(float)taxPercent/100);
	}

    

	public static int sumGrossPrice(Book[] books) {
	    int sum = 0;
	    for (int i = 0; i < books.length; i++) {
			if(books[i].getTax() == 100) {
				books[i].setTax(100);
			}
			sum += books[i].getTaxedValue();
		}
	    return sum;
	}
	

	
	
	public double getTax() {
		return this.taxPercent;
	}

	private int taxPercentDefault;
	
	@Override
	public void setTax(double tax) {
		if (tax == 10 || tax == 0 || tax == -1 || tax == 100) {
			if (tax == 10) {
				this.taxPercent = 100;
			}
			else if (tax == 100) {
				this.taxPercent = 10;
			}
			else if (tax == 0) {
				this.taxPercent = 270;
			}
			else {
				this.taxPercent = 270;
			}
		
		}
		else {
			if (tax > 0) {
				this.taxPercent = (int)tax;
			}
			else {
				
				this.taxPercent = taxPercentDefault;
			}
		}
		
	}

	

	

    

   

    


	

	
	

	

	

	
    
    
    
    

}
