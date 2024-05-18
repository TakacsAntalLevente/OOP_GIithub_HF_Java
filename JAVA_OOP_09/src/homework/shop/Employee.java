package homework.shop;

import homework.tax.Taxable;

public class Employee implements Taxable {

	private String name;
	private double salary;
	private double SZJA = 15;
	private double taxPercent = 15;

	public Employee(String name, double salary) {
		super();
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	@Override
	public void setTax(double tax) {
		this.taxPercent = 15;
	}

	@Override
	public double getTax() {
		return salary * taxPercent / 100.0;
	}

	@Override
	public double getTaxedValue() {
		return salary - getTax();
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Salary: " + salary + ", Tax amount: " + getTax()+ name;
	}

}
