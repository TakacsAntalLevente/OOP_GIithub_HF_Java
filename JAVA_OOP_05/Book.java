package homework;

public class Book {
	private String author;
	private String title;
	private int yearOfPublication;
	private int price;
	
	
	public void increasePrice(float percentage) {
		if (percentage > 0) {
			price = price + Math.round(price*percentage/100);
		}	
		
	}

	java.time.LocalDate currentDate = java.time.LocalDate.now();
	int year = currentDate.getYear();
	

	public Book(String author, String title, int yearOfPublication, int price) {
		this.author = author;
		this.title = title;
		this.yearOfPublication = yearOfPublication;
		this.price = price;
	}

	public Book(String author, String title) {
		this.author = author;
		this.title = title;
		this.price = 2500;
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

	

	public String displayInfo() {
		return author + ": " + title + ", " + yearOfPublication + ". Price: " + price + " Ft";
	}

	@Override
	public String toString() {
		return author + ": " + title + ", " + yearOfPublication + ". Price: " + price + " Ft";

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

	public int getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(int yearOfPublication) {
		if (yearOfPublication >= 1950 && yearOfPublication <= 2030)
			this.yearOfPublication = yearOfPublication;
		else
			this.yearOfPublication = 2030;
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
	}


