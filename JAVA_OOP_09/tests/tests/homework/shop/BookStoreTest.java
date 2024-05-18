package tests.homework.shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import homework.bookhierachy.Book;
import homework.shop.BookStore;
import homework.shop.Employee;

class BookStoreTest {
	protected int a = 0;
	
	@Test
	@DisplayName("Az addToStok()-nak fel kell vennie a paraméterében kapott könyvet a stock tömbbe")
	void testAddToStock() {
		BookStore bookstore = new BookStore();
		int expectedSizeOfStock = 1;
		bookstore.addToStock(new Book("author", "title3", 3000, 300, "COOK"));
		assertEquals(expectedSizeOfStock, bookstore.getStock().size());
	}

	@Test
	@DisplayName("Az addToStaff()-nak fel kell vennie a paraméterében kapott alkalamozzatat a staff tömbbe")
	void testAddToStaff() {
		BookStore bookstore = new BookStore();
		int expectedSizeOfStaff = 1;
		bookstore.addToStaff(new Employee("Béla", 200000));
		assertEquals(expectedSizeOfStaff, bookstore.getStaff().size());
	}

	@Test
	@DisplayName("A listStaff()-nak vissza kell adnia a kilistázott elemek számát")
	void testListStaff() {
		BookStore bookstore = new BookStore();
		int expectedSizeOfList = 3;
		bookstore.addToStaff(new Employee("Béla3", 200000));
		bookstore.addToStaff(new Employee("Béla2", 250000));
		bookstore.addToStaff(new Employee("Béla1", 300000));
		assertEquals(expectedSizeOfList, bookstore.listStaff());
	}

	@Test
	@DisplayName("A listStock()-nak vissza kell adnia a kilistázott elemek számát")
	void testListStore() {
		BookStore bookstore = new BookStore();
		int expectedSizeOfList = 3;
		bookstore.addToStock(new Book("author", "title3", 3000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title2", 2000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title1", 1000, 300, "COOK"));		
		assertEquals(expectedSizeOfList, bookstore.listStock());
	}

	@Test
	@DisplayName("A sumVAT()-nak vissza kell adnia az Áfa összegét")
	void testSumVAT() {
		BookStore bookstore = new BookStore();
		int expectedSumOfVAT = 300;
		bookstore.addToStock(new Book("author", "title3", 3000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title2", 2000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title1", 1000, 300, "COOK"));		
		assertEquals(expectedSumOfVAT, bookstore.sumVAT());
	}
	
	@Test
	@DisplayName("A sumIncomTax()-nak vissza kell adnia a z SZJA összegét összegét")
	void testSumIncomTax() {
		BookStore bookstore = new BookStore();
		int expectedSumOfIncomminTax = 112500;
		bookstore.addToStaff(new Employee("Béla3", 200000));
		bookstore.addToStaff(new Employee("Béla1", 250000));
		bookstore.addToStaff(new Employee("Béla1", 300000));
		assertEquals(expectedSumOfIncomminTax, bookstore.sumIncomTax());
	}

	@Test
	@DisplayName("A sortBayTitle() cím szerint ABC sorba rendezi a könyveket")
	void testSortByTitle() {
		BookStore bookstore = new BookStore();
		String expectedTitle = "title1";
		bookstore.addToStock(new Book("author", "title3", 3000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title2", 2000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title1", 1000, 300, "COOK"));
		bookstore.sortByTitle();
		assertEquals(expectedTitle, bookstore.getStock().get(0).getTitle());
	}

	@Test
	@DisplayName("A reverseSortByPrice() ár szerint csökkenő sorrendbe rendezi a könyveket")
	void testReverseSortByPrice() {
		BookStore bookstore = new BookStore();
		String expectedName = "title1";
		bookstore.addToStock(new Book("author", "title3", 1000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title2", 2000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title1", 3000, 300, "COOK"));
		bookstore.reverseSortByPrice();
		assertEquals(expectedName, bookstore.getStock().get(0).getTitle());
	}
		
}
