import BookClass.BookClass;

import java.util.Objects;
import java.util.Scanner;

public class LibraryRunner {
    static final int MAX_BOOKS = 10;
    static int booksAvailable = 0;
    static BookClass[] bookClasses = new BookClass[MAX_BOOKS];
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.print("\n\n----- Welcome to the Automatic Book Library RU -----");

        while (true) {
            int menuIndex = printMenu();
            scanner.nextLine();

            /* Menu controller */
            if (menuIndex == 1) {
                addBook();
            } else if (menuIndex == 2) {
                removeBook();
            } else if (menuIndex == 3) {
                SearchBook();
            } else if (menuIndex == 4) {
                getAllBooks();
            } else if (menuIndex == 5) {
                /* exit library */
                System.out.println("Good bye!");
                break;
            } else {
                System.out.println("Invalid option :\\");
            }
        }
    }

    static int printMenu() {
        // Menu
        System.out.println("\n");
        System.out.println("1. Add a book (title and author name)");
        System.out.println("2. Remove a book");
        System.out.println("3. Search a book with the author/title name");
        System.out.println("4. List of all available books");
        System.out.println("5. Exit the library");
        System.out.println("\n----------------------------------------------------");

        // Input
        System.out.print("\nPlease enter an option:  ");

        return scanner.nextInt();
    }


    static void addBook() {
        /* If the limit is over */
        if (booksAvailable >= MAX_BOOKS) {
            System.out.println("Sorry, the library book capacity reached to it's limit.");
            System.out.println("You cannot add books, please try again later :(");
            return;
        }

        /* Get book title and author name from user input */
        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the book's author name: ");
        String author = scanner.nextLine();

        /* Check for duplication */
        if (queryBook(title) != -1) {
            System.out.println("This book already exists in this library!");
            return;
        }

        /* Add a book */
        booksAvailable++;
        BookClass bookClass = new BookClass(title, author);
        bookClasses[booksAvailable - 1] = bookClass;
        System.out.println("Successfully added this book to the list!");
    }

    static void removeBook() {
        if (booksAvailable <= 0) {
            System.out.println("The library is empty, you cannot remove more books!");
            return;
        }

        /* Remove a book */
        System.out.print("Enter the book's title name: ");
        String title = scanner.nextLine();

        /* Query that book and remove it from the array */
        int index = queryBook(title);
        if (index == -1) {
            System.out.println("Sorry, this book is not available.");
            return;
        }
        removeBookFromList(index);
        System.out.println("Successfully removed this book from the list!");
    }

    static void getAllBooks() {
        if (booksAvailable == 0) {
            System.out.println("Sorry, the library is empty at this moment, please come later :(");
            return;
        }
        System.out.println("\n\n------ All books list ------");
        for (int i = 0; i < booksAvailable; i++) {
            System.out.printf(
                    "%d. Book title: %s. Author name: %s\n", i + 1, bookClasses[i].getBookTitle(), bookClasses[i].getBookAuthor()
            );
        }
    }

    static void SearchBook() {
        System.out.print("Enter the book/author name: ");
        String searchParam = scanner.nextLine();

        int results = 0;

        System.out.println("Query result: ");

        for (int i = 0; i < booksAvailable; i++) {
            if (Objects.equals(bookClasses[i].getBookTitle(), searchParam) || Objects.equals(bookClasses[i].getBookAuthor(), searchParam)) {
                results++;
                System.out.printf(
                        "%d. Book title: %s. Author name: %s\n", results, bookClasses[i].getBookTitle(), bookClasses[i].getBookAuthor()
                );
            }
        }
        if (results == 0) {
            System.out.println("Empty!");
        } else {
            System.out.printf("%d results", results);
        }
    }


    /* Utility method */
    static int queryBook(String title) {
        int findIndex = -1;
        for (int i = 0; i < booksAvailable; i++) {
            if (Objects.equals(title, bookClasses[i].getBookTitle())) {
                findIndex = i;
                break;
            }
        }
        return findIndex;
    }

    /* Utility method */
    static void removeBookFromList(int index) {
        for (int i = index; i < booksAvailable - 1; i++) {
            bookClasses[index] = bookClasses[index + 1];
        }
        bookClasses[booksAvailable - 1] = null;
        booksAvailable--;
    }
}
