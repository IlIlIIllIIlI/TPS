import java.util.Objects;

public class Library {
    String name;
    Book[] books;
    int bookCount;

    Library(String name,Book[] books){
        this.bookCount=books.length;
        if (bookCount>=10){
            System.out.println("Please provide atmost 10 books");
            return;
        }
        this.name=name;
        this.books = new Book[10];
        for (int i = 0; i < this.bookCount ; i++) {
            this.books[i]=new Book(books[i].title,books[i].author,books[i].pages);
        }
    }

    public void addBook(Book book){
        if (bookCount>=10){
            System.out.println("This library is full");
            return;
        }

        this.books[bookCount]=book;
        bookCount++;
    }
    public void displayAllBooks(){
        for (int i = 0; i < this.bookCount ; i++) {
            System.out.println(this.books[i].title);
        }
    }

    public int findBook(String title){
        for (int i = 0; i < this.bookCount ; i++) {
            if (Objects.equals(this.books[i].title, title)){
                System.out.println(this.books[i].title+"Exists");
                return i;
            }
        }

        return -1;
    }

    public int countAvailableBooks(){
        int freeBook =0;
        for (int i = 0; i < this.bookCount ; i++) {
            if (!this.books[i].isBorrowed){
                freeBook++;
            }
        }

        return freeBook;
    }

    public void borrowBook(String title){
        int bookIndex = findBook(title);
        if (bookIndex!=-1){
            if (this.books[bookIndex].isBorrowed){
                System.out.println("This book is already borrowed");
                return;
            }else{
                this.books[bookIndex].borrow();
                return;
            }
        }else {
            System.out.printf("This Book doesn't exist");
            return;
        }
    }


}
