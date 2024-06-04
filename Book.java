import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
//import java.util.Date;


//This class includes all the attributes and methods of a Book object.
//Book objects are used in the LibrarySystem class
public class Book {

    //All attributes of a Book are private since there's no need to make them public. (Principle of least privilege)
    private final Integer ID;    // Only added once on book object generation
    protected String name;
    protected String category;
    private boolean borrowingState;
    private long borrowingPeriod;   // in days
    protected LocalDate borrowingDate;
    protected LocalDate returnDate;

    public Book(Integer ID, String name, String category) {
        this.ID = ID;
        this.name = name;
        this.category = category;
        this.borrowingState = false;     // Initially false unless set
        this.borrowingPeriod = 0;

        //Initialized with current dates
        this.borrowingDate = LocalDate.now();
        this.returnDate=LocalDate.now();
    }


    public boolean isBorrowed() {
        return borrowingState;
    }

    public void setBorrowingState(boolean borrowingState, LocalDate borrowingDate, LocalDate returnDate) {        
        //if state argument is true =  a book is being borrowed
        //set the immutable LocalDate variables and find the borrowing period
        this.borrowingState = borrowingState;

        //Edit the last saved borrowing date by adding the difference in days until the new one.
        //Then reassign the reference variable to handle immutability
        LocalDate newBorrowingDate = this.borrowingDate.plusDays(ChronoUnit.DAYS.between(this.borrowingDate, borrowingDate));
        this.borrowingDate = newBorrowingDate;

        LocalDate newReturnDate = this.returnDate.plusDays(ChronoUnit.DAYS.between(this.returnDate, returnDate));
        this.returnDate=newReturnDate;

        //borrowing period in days as a difference
        this.borrowingPeriod =ChronoUnit.DAYS.between(borrowingDate, returnDate);

    }
    

    //Used in the display of the library status in the LivrarySystem class    
    public String getBookDetails(){
        
        if(this.isBorrowed()){
            return
            "<html> Book name: <html> "+ this.name+"<html>  ID: <html>"+ this.ID+ "<html> Borrowing status: <html>"
            + this.isBorrowed()+"<html> <br> <html>"
            +"<html> Borrowing date: <html>"+this.borrowingDate.toString()
            + " < html> <br>  Borrowing Period:<html> "+ this.borrowingPeriod+"<html> days. <br> Return <html>"+this.returnDate.toString();
        }
        else{
            return "<html> Book name: <html> "+ this.name+"<html>  ID: <html>"+ this.ID + "<html> Borrowing status: <html>"+ this.isBorrowed();
        }
    }
}