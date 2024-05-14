package homework;

public class Book {
	private String author;
	private String title;
	private final int yearOfPublication;
	private int price;
	private int pages;
	
	
	public void increasePrice(float percentage) {
		if (percentage > 0) {
			price = price + Math.round(price*percentage/100);
		}	
		
	}

	java.time.LocalDate currentDate = java.time.LocalDate.now();
	int year = currentDate.getYear();
	

	public Book(String author, String title, int price, int pages) {
		this.author = author;
		this.title = title;
		this.yearOfPublication = year;
		this.price = price >= 0 ? price : 0;
        this.pages = pages >= 0 ? pages : 0;
	}

	public Book(String author, String title) {
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

	

	public String displayInfo() {
		return author + ": " + title + ", " + yearOfPublication + ". Price: " + price + " Ft"+", "+"Pages: "+pages;
	}

	@Override
	public String toString() {
		return author + ": " + title + ", " + yearOfPublication + ". Price: " + price + " Ft"+", "+"Pages: "+pages;

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
        if (this.pages<0) {
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
	}


