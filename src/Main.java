public class Main {
    public static void main(String[]args){
        Book b1 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 224);
        Book b2 = new Book("1984", "George Orwell", 328);
        Book b3 = new Book("Clean Code", "Robert C. Martin", 464);
        Book[] books = {b1,b2};
        Library lib = new Library("Libraby",books);
        System.out.println(">>> Demo book operations : ");
        b1.displayInfo();
        b1.borrow();
        b1.displayInfo();
        b1.borrow();
        b1.returnBook();
        b1.displayInfo();
        System.out.println();

        System.out.println(">>> Library operations");
        lib.displayAllBooks();
        lib.addBook(b3);

        System.out.println("Available books count: " + lib.countAvailableBooks());
        System.out.println();

        lib.borrowBook("1984");
        System.out.println("Available books count after borrowing 1984: " + lib.countAvailableBooks());
        System.out.println();

        lib.borrowBook("Nonexistent Book");

        lib.findBook("Clean Code");

        lib.borrowBook("1984");

        Book found = lib.findBook("1984");
        if (found != null) {
            found.returnBook();
        }
        System.out.println("Available books count at end: " + lib.countAvailableBooks());
    }
}
