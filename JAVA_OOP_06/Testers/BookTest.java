package tests.homework;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import homework.Book;


public class BookTest{
	
	protected static final String author = "J.K. Rowling";
	protected static final String title = "Harry Potter";
	protected static final int yearOfPub = 2008;
	protected static final int price = 3500;
	protected static final int pages = 111;
	
	protected Book createValidBook() {
		return new Book(author, title, price, pages);
	}
	
	@Test
	@DisplayName("A setPrice-nak az 1000 feletti �rt�ket �rintetlen�l kell hagynia!")
	public void setPrice_Above999_ShouldNotBeChanged()
	{
		Book book = createValidBook();
		book.setPrice(price);

		assertEquals(price, book.getPrice());
	}
	
	@Test
	@DisplayName("Az increasePrice-nak pozit�v �rt�kre m�dos�tania kell az �rat!")
	public void increasePrice_ByAPositiveValue_ShouldChangePrice()
	{
		Book book = createValidBook();
		int originalPrice = 3500;
		book.setPrice(originalPrice);
		int expectedIncreasedPrice = 4025;
		book.increasePrice(15);

		assertEquals(expectedIncreasedPrice, book.getPrice());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -10})
	@DisplayName("Az increasePrice-nak nem pozit�v �rt�kre nem szabad m�dos�tania az �rat!")
	public void increasePrice_By0OrNegativeValue_ShouldNotChangePrice(int priceInc)
	{
		Book book = createValidBook();
		int originalPrice = 3500;
		book.setPrice(originalPrice);
		int expectedIncreasedPrice = book.getPrice();
		book.increasePrice(priceInc);

		assertEquals(expectedIncreasedPrice, book.getPrice());
	}
	
	@ParameterizedTest
	@CsvSource({"1004,1104", "1005,1106", "1006,1107"})
	@DisplayName("Az increasePrice-nak t�rt eredm�ny eset�n kerek�tenie kell a matematikai szab�lyoknak megfelel�en!")
	public void increasePrice_FractionalResult_ShouldBeRoundedAccordingToArithmeticRules(int originalPrice, int expectedPrice)
	{
		Book book = createValidBook();
		book.setPrice(originalPrice);
		book.increasePrice(10);

		assertEquals(expectedPrice, book.getPrice());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-1001, -1000, -999, 0, 999})
	@DisplayName("Az setPrice-nak az 1000 alatti �rt�keket 1000-re kell �ll�tania")
	public void setPrice_Below1000_ShouldBeCorrectedTo1000(int price)
	{
		Book book = createValidBook();
		book.setPrice(price);
		assertEquals(1000, book.getPrice());
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
		Book book = new Book(author, title);
		assertEquals(2500, book.getPrice());
	}
	
	@Test
	@DisplayName("A 2 param�teres konstruktornak 100-ra kell �ll�tania az oldalsz�mot!")
	public void _2ParamConstructor_ShouldSetPagesTo100()
	{
		Book book = new Book(author, title);
		assertEquals(100, book.getPages());
	}
	
	@ParameterizedTest
	@CsvSource({"1000,1000", "0,0", "-1000,0"})
	@DisplayName("A 4 param�teres konstruktornak az �rat a megadott pozit�v �rt�kre kell �ll�tania, vagy 0-ra, ha az nem pozit�v!")
	public void _4ParamConstructor_ShouldSetPriceToGivenPositiveValueOr0Otherwise(int setPrice, int expectedPrice)
	{
		Book book = new Book(author, title, setPrice, pages);

		assertEquals(expectedPrice, book.getPrice());
	}
	
	@ParameterizedTest
	@CsvSource({"1000,1000", "0,0", "-1000,0"})
	@DisplayName("A 4 param�teres konstruktornak az oldalsz�mot a megadott pozit�v �rt�kre kell �ll�tania, vagy 0-ra, ha az nem pozit�v!")
	public void _4ParamConstructor_ShouldSetPagesToGivenPositiveValueOr0Otherwise(int setPages, int expectedPages)
	{
		Book book = new Book(author, title, price, setPages);

		assertEquals(expectedPages, book.getPages());
	}
	
	@Test
	@DisplayName("Az toString kimenet�nek tartalmaznia kell a c�met")
	public void toString_ResultShouldContainTitle()
	{
		Book book = createValidBook();
		String result = book.toString();
		assertTrue(result.contains(title),
				"A toString �ltal el��ll�tott string nem tartalmazza az �rat!");
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
				"A toString �ltal el��ll�tott string nem tartalmazza az �rat!");
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
	@DisplayName("Az toString kimenet�nek tartalmaznia kell az �rat")
	public void toString_ResultShouldContainPages()
	{
		Book book = createValidBook();
		String result = book.toString();
		assertTrue(result.contains(Integer.toString(price)),
				"A toString �ltal el��ll�tott string nem tartalmazza az �rat!");
	}
	
	@ParameterizedTest
	@CsvSource({"100,-1", "100,-123", "100,-456753"})
	@DisplayName("A setPages negat�v bemenetre nem szabad,hogy v�ltoztassa a pages �rt�k�t!")
	public void setPages_ForNegativeValues_ShouldNotChangePages(int startPages, int setPages)
	{
		Book book = new Book(author, title, price, startPages);
		book.setPages(setPages);

		assertEquals(startPages, book.getPages());
	}
	
	@Test
	@DisplayName("A getLonger az els� k�nyvet adja vissza azonos oldalsz�m eset�n!")
	public void getLonger_ForEqualPages_ShouldReturnFirstBook()
	{
		Book bookA = new Book(author, title, price, 1234);
		Book bookB = new Book(author, title, price, 1234);

		Book longer = Book.getLonger(bookA, bookB);
		
		assertEquals(longer, bookA);
	}
	
	@ParameterizedTest
	@CsvSource({"1,2,1", "2,1,0"})
	@DisplayName("A getLonger a nagyobb oldalsz�mmal rendelkez� k�nyvet adja vissza a 2 param�ter k�z�l!")
	public void getLonger_ForDifferingPages_ShouldReturnTheLongerBook(int bookAPages, int bookBPages, int longerIndex)
	{
		Book[] books = {new Book(author, title, price, bookAPages),
				new Book(author, title, price, bookBPages)};

		Book longer = Book.getLonger(books[0], books[1]);
		
		assertEquals(longer, books[longerIndex]);
	}
	
	@ParameterizedTest
	@CsvSource({"1, false", "2, true", "333, false", "444, true"})
	@DisplayName("A hasEvenPages igazat ad vissza, ha p�ros a pages, hamisat ellenkez� esetben!")
	public void hasEvenPages_ShouldReturnTrueIfPagesIsEvenFalseOtherwise(int pages, boolean isEven)
	{
		Book book = new Book(author, title, price, pages);

		assertEquals(isEven, book.hasEvenPages());
	}
	
	@ParameterizedTest
	@CsvSource({"1, 2, 3, 3", "3, 2, 1, 3", "2, 3, 1, 3", "2, 1, 3, 3"})
	@DisplayName("A getLongestBook visszaadja a leghosszabb k�nyvet!")
	public void getLongestBook_ShouldReturnTheBookWithTheMostPages(int pagesA, int pagesB,
			int pagesC, int longestPages)
	{
		Book[] books = {new Book(author, title, price, pagesA),
				new Book(author, title, price, pagesB),
				new Book(author, title, price, pagesC)};
		
		Book longest = Book.getLongestBook(books);
		
		assertEquals(longestPages, longest.getPages());
	}
	
	@ParameterizedTest
	@CsvSource({"1, 2, 4, 5, 4", "4, 2, 1, 5, 4", "5, 4, 2, 1, 4", "2, 1, 5, 4, 4"})
	@DisplayName("A getLongestEvenPagesBook visszaadja a leghosszabb p�ros oldalsz�m� k�nyvet!")
	public void getLongestEvenPagesBook_ShouldReturnTheBookWithTheMostEvenPages(int pagesA, int pagesB,
			int pagesC, int pagesD, int longestPages)
	{
		Book[] books = {new Book(author, title, price, pagesA),
				new Book(author, title, price, pagesB),
				new Book(author, title, price, pagesC),
				new Book(author, title, price, pagesD)};
		
		Book longest = Book.getLongestEvenPagesBook(books);
		
		assertEquals(longestPages, longest.getPages());
	}
	
	@ParameterizedTest
	@CsvSource({"1, 3, 5, 7"})
	@DisplayName("A getLongestEvenPagesBook visszaadja a leghosszabb p�ros oldalsz�m� k�nyvet!")
	public void getLongestEvenPagesBook_ForArraysWithNoEvenPageBooks_ShouldReturnNull(int pagesA, int pagesB,
			int pagesC, int pagesD)
	{
		Book[] books = {new Book(author, title, price, pagesA),
				new Book(author, title, price, pagesB),
				new Book(author, title, price, pagesC),
				new Book(author, title, price, pagesD)};
		
		Book longest = Book.getLongestEvenPagesBook(books);
		
		assertNull(longest);
	}
}
