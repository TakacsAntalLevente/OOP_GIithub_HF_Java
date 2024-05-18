package homework;

import homework.EBook ;

import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        System.out.print("Adja meg az E-könyvek számát (1-10): ");
        int n = readInt(scanner, 1, 10);

        EBook[] eBooks = new EBook[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Adja meg az " + (i + 1) + ". E-könyv adatait:");
            System.out.print("Szerző: ");
            String author = scanner.nextLine();
            System.out.print("Cím: ");
            String title = scanner.nextLine();
            System.out.print("Ár: ");
            int price = scanner.nextInt();
            System.out.print("Stílus: ");
            scanner.nextLine(); 
            String style = scanner.nextLine();
            System.out.print("E-cím: ");
            String url = scanner.nextLine();

            eBooks[i] = new EBook(author, title, price,price, style, url);
        }

        System.out.println("\nBeolvasott E-könyvek:");
        for (EBook eBook : eBooks) {
            System.out.println(eBook);
        }
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