package tests.homework.tax;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import homework.tax.Taxable;

public class TaxableTest {
	
	@Test
	@DisplayName("Az áfakulcs alapértelmezett értéke 27% legyen!")
	public void taxPercent_ShouldBe27_ByDefault()
	{
		assertEquals(27, Taxable.taxPercent);
	}
	
}
