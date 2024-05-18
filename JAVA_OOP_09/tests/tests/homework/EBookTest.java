package tests.homework;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import homework.bookhierachy.*;



public class EBookTest{
	
	protected static final String author = "J.K. Rowling";
	protected static final String title = "Harry Potter";
	protected static final int yearOfPub = 2008;
	protected static final int price = 3500;
	protected static final int pages = 111;
	protected static final String style = "guide";
	
	protected Book createValidBook() {
		return new Book(author, title, price, pages, style);
	}
	
	/**
	 * Az EBoook oszt�ly tesztjei
	 */
	@Test
	@DisplayName("Az Ebook toString kimenet�nek tartalmaznia kell az url-t")
	public void toString_ResultShouldContainUrl()
	{
		String url = "https:ebook.com";
		EBook ebook = new EBook("author", "title", 2000, 200, style, url);
		String result = ebook.toString();
		assertTrue(result.contains(url),
				"A toString �ltal el��ll�tott string nem tartalmazza az url-t!");
	}

}