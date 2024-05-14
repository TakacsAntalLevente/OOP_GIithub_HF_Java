package homework;

import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Adja meg a könyvek számát (1-10): ");
        int n = readInt(scanner, 1, 10);

        Book[] books = new Book[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Adja meg a(z) " + (i + 1) + ". könyv adatait:");
            System.out.print("Szerző: ");
            String author = scanner.nextLine();
            System.out.print("Cím: ");
            String title = scanner.nextLine();
            System.out.print("Ár: ");
            int price = scanner.nextInt();
            System.out.print("Oldalszám: ");
            int pages = scanner.nextInt();
            scanner.nextLine(); 

            books[i] = new Book(author, title, price, pages);
        }

        System.out.println("\nBeolvasott könyvek:");
        Book.listBookArray(books);

        Book longestBook = Book.getLongestBook(books);
        System.out.println("\nA leghosszabb könyv adatai:");
        System.out.println(longestBook);

        Book longestEvenPagesBook = Book.getLongestEvenPagesBook(books);
        if (longestEvenPagesBook != null) {
            System.out.println("\nA leghosszabb páros oldalszámú könyv adatai:");
            System.out.println(longestEvenPagesBook);
        } else {
            System.out.println("\nNincs páros oldalszámú könyv a tömbben.");
        }

        Book.authorStatistics(books);
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
