//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Book {
    String title;
    String author;
    int pages;
    boolean isBorrowed;

    Book(String title, String author, int pages){
        this.title=title;
        this.author=author;
        this.pages=pages;
        this.isBorrowed=false;
    }

    public void borrow() {
        if (this.isBorrowed) {
            System.out.println("This book is already borrowed");
            return;
        }
        this.isBorrowed = true;
        System.out.println("You successfully borrowed the book");
    }

    public void returnBook(){
        if (!this.isBorrowed) {
            System.out.println("This book is already free");
            return;
        }
        this.isBorrowed=false;
        System.out.println("Book got returned");
    }

    public void displayInfo(){
        System.out.println("Title : "+this.title);
        System.out.println("Author : "+this.author);
        System.out.println("Pages : "+this.pages);
        System.out.println("Borrowed : "+this.isBorrowed);
    }

    public String getTitle() {
        return this.title;
    }

    public boolean getIsBorrowed(){
        return this.isBorrowed;
    }


}