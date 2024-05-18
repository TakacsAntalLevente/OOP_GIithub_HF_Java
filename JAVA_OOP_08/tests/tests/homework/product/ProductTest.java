package tests.homework.product;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import homework.Book;
import homework.product.Product;


public class ProductTest{
	
	//Mivel a product osztály absztrakt, ezért konkrét leszármazottaival tesztelünk
	
	@Test
	@DisplayName("A changeCurrency váltsa át a nem cél devizanemmel ellátott termékek devizanemét!")
	public void changeCurrency_ShouldChangeOnlyNonTargetCurrencies()
	{
		int[] prices = {3000, 6000, 9000};
		int[] expectedPrices = {3000, 6000, 9000};
		
		Product[] books = {
				new Book("A", "a", prices[0], 1, "horror"),
				new Book("B", "b", prices[1], 1, "horror"),
				new Book("C", "c", prices[2], 1, "scifi")
		};
		
		books[0].setCurrency("Euro");
		books[2].setCurrency("Euro");
		
		Product.changeCurrency(books, "Ft");
		
		for (int i = 0; i < books.length; i++) {
			Product product = books[i];
			assertTrue(product.getCurrency().equals("Ft"));
			assertEquals(expectedPrices[i], product.getPrice());
		}
	}
	
	@ParameterizedTest
	@CsvSource({"1000, 1000, 0", "1100, 1000, 1", "1000, 1100, 2"})
	@DisplayName("A comparePrice adjon vissza 0-t ha a 2 termék ára megegyezik, 1-et, ha az elsõ terméké nagyobb és 2-t egyébként!")
	public void comparePrice_ShouldReturn0IfPricesAreEqual1IfTheFirstIsGreaterAnd2Otherwise(int price1, int price2, int expectedComp)
	{
		Book book1 = new Book("A", "a", "style");
		book1.setPrice(price1);
		Book book2 = new Book("B", "b", "style");
		book2.setPrice(price2);
		
		assertEquals(expectedComp, Product.comparePrice(book1, book2));
	}
}