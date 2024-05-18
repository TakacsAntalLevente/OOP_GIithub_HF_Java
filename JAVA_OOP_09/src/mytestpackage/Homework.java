package mytestpackage;


import java.util.Scanner;


import homework.bookhierachy.EBook;
import homework.bookhierachy.*;
import homework.shop.BookStore;


public class Homework {
    public static void main(String[] args) {
    	BookStore bookstore = new BookStore();
		bookstore.addToStock(new Book("author", "title3", 3000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title2", 2000, 300, "COOK"));
		bookstore.addToStock(new Book("author", "title1", 1000, 300, "COOK"));
		
		bookstore.listStock();
    }

 
    public static int readInt(Scanner scanner, int min, int max) {
        int input;
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Érvénytelen bemenet! Kérem adjon meg egy egész számot: ");
                scanner.next(); 
            }
            input = scanner.nextInt();
            if (input < min || input > max) {
                System.out.print("Az értéknek " + min + " és " + max + " között kell lennie! Kérem próbálja újra: ");
            }
        } while (input < min || input > max);
        return input;
    }
    
}