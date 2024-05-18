package homework.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import homework.bookhierachy.*;

public class BookStore implements Comparator<Book> {
	ArrayList<Book> stock = new ArrayList<>();
	ArrayList<Employee> staff = new ArrayList<>();

	public ArrayList<Book> getStock() {
		return stock;
	}

	public ArrayList<Employee> getStaff() {
		return staff;
	}

	public void setStaff(ArrayList<Employee> staff) {
		this.staff = staff;
	}

	public void setStock(ArrayList<Book> stock) {
		this.stock = stock;
	}

	public void addToStock(Book book) {
		stock.add(book);
	}

	public void addToStaff(Employee employee) {
		staff.add(employee);
	}

	public int listStock() {
		int help = stock.size();
		stock.toString();

		return help;
	}

	public int listStaff() {
		int help = staff.size();
		staff.toString();

		return help;
	}

	public int sumVAT() {
		int help = 0;
		for (int i = 1; i < stock.size(); i++) {
			help =help+ stock.get(i).getPrice();
		}
		return 5*help/50;
	}

	public int sumIncomTax() {
		int help = 0;
		for (int i = 0; i < staff.size(); i++) {
			help += staff.get(i).getTax();
		}
		return help;
	}

	public void sortByTitle() {
		Collections.sort(stock, Comparator.comparing(Book::getTitle));
	}

	@Override
	public int compare(Book o1, Book o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
	public void reverseSortByPrice() {
		Collections.sort(stock, Comparator.comparing(Book::getPrice).reversed());
	}

}
