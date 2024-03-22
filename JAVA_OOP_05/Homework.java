package homework;

public class Homework {

	public static void main(String[] args) {

		Book book = new Book(null, null);
		
		book.setAuthor("J.K. Rowling");
		book.setTitle("Harry Potter");
		book.setYearOfPublication(2008);
		book.setPrice(1004);
		System.out.println(book.displayInfo());

		
		
		Book book2 = new Book(null, null);
		book2.setAuthor("Jozsef Attila");
		book2.setTitle("Nem tudom...");
		book2.getPrice();
		book2.getYearOfPublication();
		System.out.println(book2.displayInfo());
		
		
		int result = Book.comparePublicationDate(book, book2);
        System.out.println(result);
        
        
        System.out.println("Increased Price: ");
        book.increasePrice(10);
        System.out.println(book.getPrice());
	}
}
