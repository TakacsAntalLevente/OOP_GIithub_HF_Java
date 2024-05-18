package tests.homework.shop;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import homework.bookhierachy.*;
import homework.shop.Employee;

class EmployeeTest {
	protected static final String name = "Pista";
	protected static final int salary = 200000;
	
	protected Employee createValidEmployee( ) {
		return new Employee(name, salary);
	}
	
	@Test
	@DisplayName("A getTax()-nak a fizetendő SZJ-t kell visszaadnia")
	void shouldReturnIncommingTaxValue() {
		double expectedIncommingTax = 30000.0;
		Employee e = createValidEmployee();
		assertEquals(expectedIncommingTax, e.getTax());
	}

	@Test
	@DisplayName("A getTaxedValue()-nak a fizetendő SZJ levonása utáni fizetést kell visszaadnia")
	void shouldReturnSalaryAfterTax() {
		int expectedIncomming = 170000;
		Employee e = createValidEmployee();
		assertEquals(expectedIncomming, (int)Math.round(e.getTaxedValue()));
	}

	@Test
	@DisplayName("Az toString kimenetének tartalmaznia kell a nevet")
	public void toString_ResultShouldContainName()
	{
		Employee e = createValidEmployee();
		String result = e.toString();
		assertTrue(result.contains(name),
				"A toString által elõállított string nem tartalmazza a nevet!");
	}

	@DisplayName("Az toString kimenetének tartalmaznia kell a fizetést")
	public void toString_ResultShouldContainSalary()
	{
		Employee e = createValidEmployee();
		String result = e.toString();
		assertTrue(result.contains(String.valueOf(salary)),
				"A toString által elõállított string nem tartalmazza a fizetést!");
	}
	
	@DisplayName("Az toString kimenetének tartalmaznia kell az SZJ összegét")
	public void toString_ResultShouldContainIncommingTax()
	{
		Employee e = createValidEmployee();
		String result = e.toString();
		assertTrue(result.contains(String.valueOf(e.getTax())),
				"A toString által elõállított string nem tartalmazza a az SZJ összegét!");
	}
	

}
