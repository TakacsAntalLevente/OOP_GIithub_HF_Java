package tests.homework;

import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import homework.Book;
import homework.EBook;


public class BookTest{
	
	protected static final String author = "J.K. Rowling";
	protected static final String title = "Harry Potter";
	protected static final int yearOfPub = 2008;
	protected static final int price = 3500;
	protected static final int pages = 111;
	protected static final String style = "guide";
	
	protected Book createValidBook() {
		return new Book(author, title, price, pages, style);
	}
		
	@Test
	@DisplayName("A 4 param�teres konstruktornak a publik�ci� �v�t a jelen �vre kell �ll�tania!")
	public void _4ParamConstructor_ShouldSetyearOfPublicationToCurrentYear()
	{
		Book book = createValidBook();
		java.time.LocalDate currentDate = java.time.LocalDate.now();
		assertEquals(currentDate.getYear(), book.getYearOfPublication());
	}
	
	@Test
	@DisplayName("A 2 param�teres konstruktornak 2500-ra kell �ll�tania az �rat!")
	public void _2ParamConstructor_ShouldSetPriceTo2500()
	{
		Book book = new Book(author, title, style);
		assertEquals(2500, book.getPrice());
	}
	
	@Test
	@DisplayName("A 2 param�teres konstruktornak 100-ra kell �ll�tania az oldalsz�mot!")
	public void _2ParamConstructor_ShouldSetPagesTo100()
	{
		Book book = new Book(author, title, style);
		assertEquals(100, book.getPages());
	}
	
	@ParameterizedTest
	@CsvSource({"1000,1000", "0,0", "-1000,0"})
	@DisplayName("A 4 param�teres konstruktornak az �rat a megadott pozit�v �rt�kre kell �ll�tania, vagy 0-ra, ha az nem pozit�v!")
	public void _4ParamConstructor_ShouldSetPriceToGivenPositiveValueOr0Otherwise(int setPrice, int expectedPrice)
	{
		Book book = new Book(author, title, setPrice, pages, style);

		assertEquals(expectedPrice, book.getPrice());
	}
	
	@ParameterizedTest
	@CsvSource({"1000,1000", "0,0", "-1000,0"})
	@DisplayName("A 4 param�teres konstruktornak az oldalsz�mot a megadott pozit�v �rt�kre kell �ll�tania, vagy 0-ra, ha az nem pozit�v!")
	public void _4ParamConstructor_ShouldSetPagesToGivenPositiveValueOr0Otherwise(int setPages, int expectedPages)
	{
		Book book = new Book(author, title, price, setPages, style);

		assertEquals(expectedPages, book.getPages());
	}
	
	@Test
	@DisplayName("Az toString kimenet�nek tartalmaznia kell a c�met")
	public void toString_ResultShouldContainTitle()
	{
		Book book = createValidBook();
		String result = book.toString();
		assertTrue(result.contains(title),
				"A toString �ltal el��ll�tott string nem tartalmazza a c�met!");
	}
	
	@Test
	@DisplayName("Az toString kimenet�nek tartalmaznia kell a szerz�t")
	public void toString_ResultShouldContainAuthor()
	{
		Book book = createValidBook();
		String result = book.toString();
		assertTrue(result.contains(author),
				"A toString �ltal el��ll�tott string nem tartalmazza az �rat!");
	}
	
	@Test
	@DisplayName("Az toString kimenet�nek tartalmaznia kell a publik�ci� �v�t")
	public void toString_ResultShouldContainYearOfPublication()
	{
		Book book = createValidBook();
		String result = book.toString();
		java.time.LocalDate currentDate = java.time.LocalDate.now();
		assertTrue(result.contains(Integer.toString(currentDate.getYear())),
				"A toString �ltal el��ll�tott string nem tartalmazza a publik�ci� �v�t!");
	}
	
	@Test
	@DisplayName("Az toString kimenet�nek tartalmaznia kell az �rat")
	public void toString_ResultShouldContainPrice()
	{
		Book book = createValidBook();
		String result = book.toString();
		assertTrue(result.contains(Integer.toString(price)),
				"A toString �ltal el��ll�tott string nem tartalmazza az �rat!");
	}
	
	@Test
	@DisplayName("Az toString kimenet�nek tartalmaznia kell az oldalsz�mot")
	public void toString_ResultShouldContainPages()
	{
		Book book = createValidBook();
		String result = book.toString();
		assertTrue(result.contains(Integer.toString(pages)),
				"A toString �ltal el��ll�tott string nem tartalmazza az oldalsz�mot!");
	}

	@Test
	@DisplayName("Az toString kimenet�nek tartalmaznia kell a st�lust")
	public void toString_ResultShouldContainStyle()
	{
		Book book = createValidBook();
		String result = book.toString();
		assertTrue(result.contains(style),
				"A toString �ltal el��ll�tott string nem tartalmazza a st�lust!");
	}

	@ParameterizedTest
	@CsvSource({"100,-1", "100,-123", "100,-456753"})
	@DisplayName("A setPages negat�v bemenetre nem szabad,hogy v�ltoztassa a pages �rt�k�t!")
	public void setPages_ForNegativeValues_ShouldNotChangePages(int startPages, int setPages)
	{
		Book book = new Book(author, title, price, startPages, style);
		book.setPages(setPages);

		assertEquals(startPages, book.getPages());
	}
	
	@ParameterizedTest
	@CsvSource({"1104,938", "1107, 941"})
	@DisplayName("A Book descreasePrice-nak t�rt eredm�ny eset�n kerek�tenie kell a matematikai szab�lyoknak megfelel�en!")
	public void decreasePrice_FractionalResult_ShouldBeRoundedAccordingToRules(int originalPrice, int expectedPrice)
	{
		Book book = createValidBook();
		book.setPrice(originalPrice);
		book.setStyle(style);
		book.decreasePrice(10);
		assertEquals(expectedPrice, book.getPrice());
	}

	
	@Test
	@DisplayName("A getLonger az els� k�nyvet adja vissza azonos oldalsz�m eset�n!")
	public void getLonger_ForEqualPages_ShouldReturnFirstBook()
	{
		Book bookA = new Book(author, title, price, 1234, style);
		Book bookB = new Book(author, title, price, 1234, style);

		Book longer = Book.getLonger(bookA, bookB);
		
		assertEquals(longer, bookA);
	}
	
	@ParameterizedTest
	@CsvSource({"1,2,1", "2,1,0"})
	@DisplayName("A getLonger a nagyobb oldalsz�mmal rendelkez� k�nyvet adja vissza a 2 param�ter k�z�l!")
	public void getLonger_ForDifferingPages_ShouldReturnTheLongerBook(int bookAPages, int bookBPages, int longerIndex)
	{
		Book[] books = {new Book(author, title, price, bookAPages, style),
				new Book(author, title, price, bookBPages, style)};

		Book longer = Book.getLonger(books[0], books[1]);
		
		assertEquals(longer, books[longerIndex]);
	}
	
	@ParameterizedTest
	@CsvSource({"1, false", "2, true", "333, false", "444, true"})
	@DisplayName("A hasEvenPages igazat ad vissza, ha p�ros a pages, hamisat ellenkez� esetben!")
	public void hasEvenPages_ShouldReturnTrueIfPagesIsEvenFalseOtherwise(int pages, boolean isEven)
	{
		Book book = new Book(author, title, price, pages, style);

		assertEquals(isEven, book.hasEvenPages());
	}
	
	@ParameterizedTest
	@CsvSource({"1, 2, 3, 3", "3, 2, 1, 3", "2, 3, 1, 3", "2, 1, 3, 3"})
	@DisplayName("A getLongestBook visszaadja a leghosszabb k�nyvet!")
	public void getLongestBook_ShouldReturnTheBookWithTheMostPages(int pagesA, int pagesB,
			int pagesC, int longestPages)
	{
		Book[] books = {new Book(author, title, price, pagesA, style),
				new Book(author, title, price, pagesB, style),
				new Book(author, title, price, pagesC, style)};
		
		Book longest = Book.getLongestBook(books);
		
		assertEquals(longestPages, longest.getPages());
	}
	
	@ParameterizedTest
	@CsvSource({"1, 2, 4, 5, 4", "4, 2, 1, 5, 4", "5, 4, 2, 1, 4", "2, 1, 5, 4, 4"})
	@DisplayName("A getLongestEvenPagesBook visszaadja a leghosszabb p�ros oldalsz�m� k�nyvet!")
	public void getLongestEvenPagesBook_ShouldReturnTheBookWithTheMostEvenPages(int pagesA, int pagesB,
			int pagesC, int pagesD, int longestPages) {
		Book[] books = {new Book(author, title, price, pagesA, style),
				new Book(author, title, price, pagesB, style),
				new Book(author, title, price, pagesC, style),
				new Book(author, title, price, pagesD, style)};
		
		Book longest = Book.getLongestEvenPagesBook(books);
		
		assertEquals(longestPages, longest.getPages());
	}
	
	@ParameterizedTest
	@CsvSource({"1, 3, 5, 7"})
	@DisplayName("A getLongestEvenPagesBook visszaadja a leghosszabb p�ros oldalsz�m� k�nyvet!")
	public void getLongestEvenPagesBook_ForArraysWithNoEvenPageBooks_ShouldReturnNull(int pagesA, int pagesB,
			int pagesC, int pagesD)
	{
		Book[] books = {new Book(author, title, price, pagesA, style),
				new Book(author, title, price, pagesB, style),
				new Book(author, title, price, pagesC, style),
				new Book(author, title, price, pagesD, style)};
		
		Book longest = Book.getLongestEvenPagesBook(books);
		
		assertNull(longest);
	}
	
	@Test
	@DisplayName("A countStyles a k�l�nb�z� st�lusok sz�m�t adja vissza!")
	public void countStylesTest()
	{
		Book[] books = {
				new Book(author, title, price, 1, "guide"),
				new Book(author, title, price, 1, "guide"),
				new Book(author, title, price, 1, "scifi")
		};
		int expectecNumberOfStyles = 2;
		
		assertEquals(expectecNumberOfStyles, Book.countStyles(books));
	}
	
	@Test
	@DisplayName("A discountBooks csak az adott st�lus� k�nyvek �r�t cs�kkenti!")
	public void discountBooksTest()
	{
		Book[] books = {
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "scifi")
		};
		int expectedPriceHorror = 1800;
		int expectedPriceScifi = 2000;
		Book.discountBooks(books, "horror");		
		assertEquals(expectedPriceHorror, books[0].getPrice());
		assertEquals(expectedPriceHorror, books[1].getPrice());
		assertEquals(expectedPriceScifi, books[2].getPrice());
	}

	@Test
	@DisplayName("A listBooksWithStyle csak az adott st�lus� k�nyveket list�zza!")
	public void listBooksWithStyleTest()
	{
		Book[] books = {
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "scifi")
		};
		int expectedSizeOfList = 2;
		assertEquals(expectedSizeOfList, Book.listBooksWithStyle(books, "horror"));
	}
	
	@Test
	@DisplayName("Az avgPrice csak az adott st�lus� k�nyvek �tlag�r�t adja vissza!")
	public void avgPriceWitExistingStyle()
	{
		Book[] books = {
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "scifi")
		};
		int expectedAvg = 2000;
		assertEquals(expectedAvg, Book.avgPrice(books, "horror"));
	}

	@Test
	@DisplayName("Az avgPrice 0-�t ad vissza, ha nincs adott st�lus� k�nyv!")
	public void avgPriceWitNonExistingStyle()
	{
		Book[] books = {
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "scifi")
		};
		int expectedAvg = 0;
		assertEquals(expectedAvg, Book.avgPrice(books, "guide"));
	}
	
	//8. h�t
	
	//Mivel a Product abstract csak innen tudjuk k�zvetetten tesztelni n�h�ny tulajdons�g�t
	
	@ParameterizedTest
	@CsvSource({"10, 100", "0, 270", "-1, 270"})
	@DisplayName("A be�ll�tott ad� m�rt�ke csak pozit�v sz�m lehet!")
	public void setTax_ShouldOnlySetPositiveValues(int setTax, int expectedTax)
	{
		Book book = createValidBook();
		book.setPrice(1000);
		book.setTax(setTax);
		
		assertEquals(expectedTax, book.getTax());
	}
	
	@Test
	@DisplayName("A p�nznem alap�rtelmezett �rt�ke Ft legyen!")
	public void currency_ShouldBeFt_ByDefault()
	{
		Book book = createValidBook();
		assertEquals("Ft", book.getCurrency());
	}
	
	@ParameterizedTest
	@CsvSource({"Ft, Ft", "Euro, Euro", "USD, Ft"})
	@DisplayName("A devizanem csak Ft, vagy Euro lehet! M�s megadott �rt�k eset�n legyen Ft!")
	public void setCurrency_ShouldOnlySetFtOrEuro(String setCurrency, String expectedCurrency)
	{
		Book book = createValidBook();
		book.setCurrency(setCurrency);
		
		assertEquals(expectedCurrency, book.getCurrency());
	}

	
	@Test
	@DisplayName("Az toString kimenet�nek tartalmaznia kell az egys�g�rat!")
	public void toString_ResultShouldContainUnitPrice()
	{
		Book book = createValidBook();
		String result = book.toString();
		//A Double.toString ott hagyja m�g�tte a .0-t �s elt�r a konkaten�lt v�ltozatt�l
		assertTrue(result.contains("" + book.getUnitPrice()),
				"A toString �ltal el��ll�tott string nem tartalmazza az brutt� �rat!");
	}
	
	@Test
	@DisplayName("Az avgPrice 0-�t ad vissza, ha nincs adott st�lus� k�nyv!")
	public void getUnitPrice_ShouldReturnTaxedValue()
	{
		Book[] books = {
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "horror"),
				new Book(author, title, 2000, 1, "scifi")
		};
		int expectedAvg = 0;
		assertEquals(expectedAvg, Book.avgPrice(books, "guide"));
	}
	
	@Test
	@DisplayName("A selectAuthors visszaadja az �sszes szerz�t, akinek van a megadott egys�g�rn�l dr�g�bb k�nyve a t�mbben!")
	public void selectAuthors_ShouldReturnAllAuthorsWhoHaveBooksAboveTheGivenUnitPrice()
	{
		Book[] books = {
				new Book("A", "a1", 2100, 1, "horror"),
				new Book("A", "a2", 1900, 1, "horror"),
				new Book("A", "a3", 2200, 1, "scifi"),
				new Book("B", "b", 2300, 1, "scifi"),
				new Book("C", "c", 1900, 1, "scifi"),
				new Book("D", "d", 1800, 1, "scifi"),
		};
		
		String[] authors = Book.selectAuthors(books, 2000);
		
		assertEquals(2, authors.length);
		
		Arrays.sort(authors);
		
		assertTrue(Arrays.binarySearch(authors, "A") >= 0);
		assertTrue(Arrays.binarySearch(authors, "B") >= 0);
	}
	
	@Test
	@DisplayName("A sumGrossPrice kisz�m�tja egy Book t�mbben a k�nyvek ad�val n�velt �ssz�r�t!")
	public void sumGrossPrice_ShouldReturnTheSumOfGrossPricesOfTheBooksInTheArray()
	{
		Book[] books = {
				new Book("A", "a1", 1000, 1, "horror"),
				new Book("A", "a2", 2000, 1, "horror"),
				new Book("A", "a3", 4000, 1, "scifi"),
		};
		
		books[1].setTax(10.0);
		books[2].setTax(25.0);
		
		assertEquals(8250, Book.sumGrossPrice(books));
	}
}
