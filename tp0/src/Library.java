import java.util.Objects;

public class Library {
    String name;
    Book[] books;
    int bookCount;

    Library(String name){
        this.name=name;
        this.books = new Book[10];
        this.bookCount=0;
    }
    Library(String name,Book[] books){
        this.bookCount=books.length;
        if (bookCount>=10){
            System.out.println("Please provide atmost 10 books");
            return;
        }
        this.name=name;
        this.books = books;
    }

    public void addBook(Book book){
        if (this.bookCount>=10){
            System.out.println("This library is full");
            return;
        }

        this.books[this.bookCount]=book;
        this.bookCount++;
    }
    public void displayAllBooks(){
        for (int i = 0; i < this.bookCount ; i++) {
            this.books[i].displayInfo();
        }
    }

    public Book findBook(String title){
        for (int i = 0; i < this.bookCount ; i++) {
            if (Objects.equals(this.books[i].title, title)){
                System.out.println(this.books[i].title+"Exists");
                return this.books[i];
            }
        }

        return null;
    }

    public int countAvailableBooks(){
        int freeBook =0;
        for (int i = 0; i < this.bookCount ; i++) {
            if (!this.books[i].getIsBorrowed()){
                freeBook++;
            }
        }

        return freeBook;
    }

    public void borrowBook(String title){
        for (int i = 0; i < this.bookCount ; i++) {
            Book book = this.books[i];
            if (book.getTitle().equalsIgnoreCase(title)&&!book.getIsBorrowed()){
                book.borrow();
                return;
            }
        }
        System.out.println("No Book available to borrow");
    }


}
