import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LibrarySystem extends JFrame implements ActionListener {

    private Integer ID=20; //Initial value to begin all IDs

    private final int maxCapacity; // Only added once at the beginning of the program
    private Integer borrowedBookCount=0;
    private HashMap<Integer,Book> booksMap;
    private HashMap<String,Integer>categoryCountMap;
    private JTextField bookIDtxt, bookNametxt, bookReturnDatetxt;
    private JLabel  userInstructionlabel,bookIDlabel,bookNamelabel,bookReturnDatelabel,categoryLabel,systemResultlabel;
    private JComboBox<String> categoryDropList;
    private JButton addButton, removeButton, borrowButton, returnButton, viewLibraryStatusButton, exitButton;

    
    public LibrarySystem(int maxCapacity) {
        //Initialzing variables
        this.maxCapacity = maxCapacity;

        this.booksMap = new HashMap<Integer,Book>();
        //Initializing the categoryCount map
        this.categoryCountMap= new HashMap<String,Integer>();
        this.categoryCountMap.put("Biology", 0);
        this.categoryCountMap.put("Maths", 0);
        this.categoryCountMap.put("History", 0);
        this.categoryCountMap.put("Chemistry",0);
        this.categoryCountMap.put("Politics", 0);
        
        initializeGUI();
    }

    private void initializeGUI(){
        JFrame frame = new JFrame();
        JPanel panel=new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        frame.setTitle("217 Project: Libray System Demo");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//Creating and adding the buttons, texts...etc.
        
        //Buttons for user operations:
        addButton = new JButton("Add Book");
        //addButton.setMaximumSize(new Dimension(10,10));
        addButton.addActionListener(this);
        
        removeButton = new JButton("Remove Book");
        //removeButton.setMaximumSize(new Dimension(5,5));
        removeButton.addActionListener(this);

        borrowButton = new JButton("Borrow Book");
        borrowButton.addActionListener(this);

        returnButton = new JButton("Return Book");
        returnButton.addActionListener(this);

        viewLibraryStatusButton = new JButton("View Library Status");
        viewLibraryStatusButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

// Labels:
    Color transparentDark = new Color(0, 0, 0, 128); // Create the color for labels Adjust alpha for transparency (0-255)

    userInstructionlabel = new JLabel("<html>User input instruction:<br> 1-Add: Insert the book title and choose a category from the drop list. <br> 2-Remove:Insert the book id to be removed <br>3-Borrow and return: insert the book ID and the return date in the format year-month-day and make sure to put a 0 in single-digit days or months.");
    userInstructionlabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding in pixels (top, left, bottom, right)
    userInstructionlabel.setOpaque(true); // Make label opaque to show background color
    userInstructionlabel.setBackground(transparentDark);
    userInstructionlabel.setForeground(Color.WHITE); // Set font color to white


    bookIDlabel= new JLabel("ID");
    bookIDlabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding in pixels (top, left, bottom, right)
    bookNamelabel=new JLabel("Name");
    categoryLabel= new JLabel("Category");
    bookReturnDatelabel= new JLabel ("Return Date");

    systemResultlabel = new JLabel("<html> <br>  <br>  <br> System response messages displayed here here<br>  <br>  <br> <html>");
    systemResultlabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding in pixels (top, left, bottom, right)   
    systemResultlabel.setOpaque(true); // Make label opaque to show background color
    systemResultlabel.setBackground(transparentDark);
    systemResultlabel.setForeground(Color.WHITE); // Set font color to white



    //bookIDtxt: User inputs this foield when removing or norrowing a book
    //Users get the system-generated ID in this field when they're adding a book

    bookIDtxt = new JTextField(" Input for removal and borrowing. Output for Adding.");        
    bookNametxt = new JTextField("Book title");
    categoryDropList = new JComboBox<>(new String[]{"Biology", "Maths", "History", "Chemistry", "Politics"});
    bookReturnDatetxt = new JTextField(" year-month-day ");


    //Adding the componets to the panel with proper grid placement
        c.fill=GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10); // 10px of space around each button
        c.weightx = 0.5; // Weight for horizontal centering
        c.weighty = 0.5; // Weight for vertical centering

        c.gridx=0;
        c.gridy=0;
        c.gridheight=5;
        panel.add(userInstructionlabel,c);
        c.gridheight=1;
        c.gridy+=6;

        c.gridwidth=1;

//ID
        panel.add(bookIDlabel,c);
        c.gridx=4;
        c.gridwidth=6;
        panel.add(bookIDtxt,c);
        c.gridy++;
        c.gridx=0;
//name
        panel.add(bookNamelabel,c);
        c.gridx=3;
        c.gridwidth=6;
        panel.add(bookNametxt,c);
        c.gridy++;
        c.gridx=0;
//category
        panel.add(categoryLabel,c);
        c.gridx=4;
        c.gridwidth=6;
        panel.add(categoryDropList,c);
        c.gridy++;
        c.gridx=0;

//returndate
        panel.add(bookReturnDatelabel,c);
        c.gridx=4;
        c.gridwidth=6;
        panel.add(bookReturnDatetxt,c);
        c.gridy++;
        c.gridx=0;

//buttons
        c.gridx=0;
        c.gridy+=2;
        c.gridwidth=1;
        panel.add(addButton,c);
        c.gridx+=2;
        panel.add(removeButton,c);
        c.gridx+=2;
        panel.add(borrowButton,c);
        c.gridx+=2;
        panel.add(returnButton,c);
        c.gridx+=2;
        panel.add(viewLibraryStatusButton,c);

        c.gridy+=2;
        c.gridx=0;
        c.gridheight=6;
        panel.add(systemResultlabel,c);

        c.gridheight=1;
        c.gridy+=7;
        c.gridx=2;
        panel.add(exitButton,c);


  
///////////////////////////////////////////////////////////
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    panel.setSize(getPreferredSize());
    panel.setOpaque(false);
    //panel.setLayout(new GridLayout());
    //Background
    frame.add(panel);
    frame.setSize(getPreferredSize());
    frame.pack();
    frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addBook();
        } 
        else if (e.getSource() == removeButton) {
            removeBook();
        }
        else if (e.getSource() == borrowButton) {
            borrowBook();
        }
        else if (e.getSource() == returnButton) {
            returnBook();
        }
        else if (e.getSource() == viewLibraryStatusButton) {
        getLibraryStatusFrame(this.getCategoryCount(),this.booksMap);
       }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void addBook() {
        Integer id=0;
        String name="";
        String category="";
        //Checking if the capacity limit exceeded
        if(this.booksMap.size() < this.maxCapacity){
            try{
            id=this.generateID();
            name=this.bookNametxt.getText().trim();
            if(name.isEmpty()) {throw new Exception();}
            else{
                category=(String) (this.categoryDropList.getSelectedItem());
                //create a new book object with the input details and add it in the Map  
                this.booksMap.put (id,new Book(id,name,category));
            // Incremintin the category count in the count map
                this.categoryCountMap.put(category, this.categoryCountMap.get(category)+1);
            //Display the generated ID and that the adding was successful
                this.bookIDtxt.setText("Generated ID:" +id.toString());
                this.systemResultlabel.setText("<html> Book with ID <html> " +id+ " <html>Successfully added. <br> <html>"+" <html> Book title:<html> "+ name+"<html> <br> Category <html>:"+category);
            }
            }
            catch(Exception e){
                this.systemResultlabel.setText("<html>Make sure to input a string book name and choose a category<html>");
                }
        }    
        else{
            this.systemResultlabel.setText("Capacity limit Exceeded. Can not add more than"+ this.maxCapacity+" books.");
        }
        }


    private void removeBook() {
        Integer id=0;
        //get the ID of the book to be removed.
        try{
             id= Integer.parseInt(this.bookIDtxt.getText().trim());
            }
        catch(Exception e){
        this.systemResultlabel.setText("<html> Make sure to input an integer ID <html>");
        }
        //if the book is found in the system
        if (this.booksMap.containsKey(id)){
            String category = this.booksMap.get(id).category;
            String name=this.booksMap.get(id).name;
            LocalDate returnDate=this.booksMap.get(id).returnDate;

            //If the book is borrowed, inform the user to wait till its back and then remove it
            if(this.booksMap.get(id).isBorrowed()){
                this.systemResultlabel.setText("<html> Book with ID <html> " +id+ " <html>is borrowed. Must wait till <html>"+returnDate+"<html> and try removing it again.<html>");
            }
            else{
            //decrement the count of this category and update the map

            Integer newcount=this.categoryCountMap.get(category) -1;
            this.categoryCountMap.put(category,newcount);

            //remove the book from the bookmap, and display the removal in bookIDtxt
            this.booksMap.remove(id);

            this.systemResultlabel.setText("<html> Book with ID <html> " +id+ " <html>Successfully removed <br> <html>"+" <html> Book title:<html> "+ name+"<html> <br> Category:"+category);
            }
        }
        else{
            this.systemResultlabel.setText("<html> Book with ID <html> " +id+"Not found in the system.");
        }      
        }

    private void borrowBook() {
        Integer id=0;
        try{
             id= Integer.parseInt(this.bookIDtxt.getText().trim());
             LocalDate returnDate = LocalDate.parse(this.bookReturnDatetxt.getText().trim());
             
        //Check if the book id exists and the book is not borrowed
        if (this.booksMap.containsKey(id)){
            if(! (this.booksMap.get(id).isBorrowed())){
                //mark the book as borrowed and update the borrow/ return dates and the borrowing period.
                //also update the borrowedBookCount
                LocalDate borrowingDate= LocalDate.now(); //Current date as the borrowing date
                this.booksMap.get(id).borrowingDate=borrowingDate;
                this.booksMap.get(id).setBorrowingState(true,borrowingDate, returnDate);

                this.borrowedBookCount++;
                //this.booksMap.get(id)
                this.systemResultlabel.setText("<html>Successfully Borrowed book. ID:<html> "+id+"<html> <br> Return in: <html>" +this.booksMap.get(id).returnDate.toString());
            }
            else{
                this.systemResultlabel.setText("Book with ID: "+id+" already borrowed."+"<html> <br> Can borrow again after return in: <html>" +this.booksMap.get(id).returnDate.toString());
            }
        // if book not in the system
        }
        else{
            this.systemResultlabel.setText("<html> Book with ID <html> " +id+"Not found in the system.");
        } 

        //In case parsing the date had a problem  
        //must include the body inside the try because LocalDate is immutable
         }
        catch(Exception e){
        this.systemResultlabel.setText("Make sure to input an integer ID and the return date in the formar (0000-00-00)(year-month-day)");
        }
        }

    //Method to return a borrowed book, invoked on pressing the reurn button
    private void returnBook() {
        //Making sure to empty the outputlabel since there would be an append simulation in this method
        systemResultlabel.setText(" ");
        Integer id=0;
        try{
            id= Integer.parseInt(this.bookIDtxt.getText().trim());
            LocalDate actualReturnDate = LocalDate.parse(this.bookReturnDatetxt.getText().trim());
            //check if the book ID exists
            if (this.booksMap.containsKey(id)){

                //Check If the book was not borrowed and return that it is on shelf.
                if(! (this.booksMap.get(id).isBorrowed())){
                    this.systemResultlabel.setText("<html> Book with ID <html> " +id+"Already on shelf. Not borrowed.");
                    }

                //if the book was indeed borowed,
                else{ 
                    LocalDate optimalRetutnDate=this.booksMap.get(id).returnDate;
                    //Compare the return date and the optimal return date
                    //show a warning if it was late and pay a fine
                    //then proceed to returning anyway
                    if(! (actualReturnDate.isBefore(optimalRetutnDate))){
                    long fine= getLateReturnFine(ChronoUnit.DAYS.between(optimalRetutnDate, actualReturnDate));
                    this.systemResultlabel.setText("<html> Book with ID <html> " +id+"<html> was late to return.<br> Must pay a fine of "+ fine+" LE.");
                    }
                    //Note that since both date arguments are the same, it sets the borrow and return dates of the returned book into the actualReturnDate, and the borrowingPeriod=0
                    this.booksMap.get(id).setBorrowingState(false, actualReturnDate, actualReturnDate);
                    this.borrowedBookCount--;
                    //concat the current output with the new to not lose the late warning if applicable.
                    this.systemResultlabel.setText(systemResultlabel.getText()+"<html> <br> Book with ID <html> " +id+"<html> Successfully returned <html>");
                }
            }
            //if the book ID was not found, display it's not in the system
            else{
                this.systemResultlabel.setText("<html> Book with ID <html> " +id+"<html> not found in the system <html>");
            }
            }

        catch(Exception e){
            this.systemResultlabel.setText("Make sure to input an integer ID and the return date in the format (0000-00-00)(year-month-day)");

        }
    }

    private long getLateReturnFine(long delayInDays){
        //10 pounds for wach delay day fine.
        return (10*delayInDays);
    }   
    
      //This method is used to get the library status report in a separate frame since it should be big.
      private void getLibraryStatusFrame(String categorystatus, HashMap<Integer,Book> booksMap){
        JFrame statusFrame = new JFrame();
        JPanel statusPanel=new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Color transparentDark = new Color(0, 0, 0, 128); // Create the color for labels Adjust alpha for transparency (0-255)

        //Shows the category count of the library
        JLabel libCategoriesStatuslabel= new JLabel(categorystatus);
        libCategoriesStatuslabel.setOpaque(true); // Make label opaque to show background color
        libCategoriesStatuslabel.setBackground(transparentDark);
        libCategoriesStatuslabel.setForeground(Color.BLUE); // Set font color to white
        //libStatuslabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        c.fill=GridBagConstraints.BOTH;
        c.insets=new Insets(10,10,10,10);
        c.gridx=0;
        c.gridy=0;
        c.weightx=0.5;
        c.weighty=0.5;
        statusPanel.add(libCategoriesStatuslabel,c);
        c.gridy+=2;

        //Number of borrowed Books:
        JLabel borrowedBooksCountlabel= new JLabel("Total Number of Borrowed Books: "+this.borrowedBookCount.toString());
        borrowedBooksCountlabel.setOpaque(true); // Make label opaque to show background color
        borrowedBooksCountlabel.setBackground(transparentDark);
        borrowedBooksCountlabel.setForeground(Color.BLUE); // Set font color to white
        statusPanel.add(borrowedBooksCountlabel,c);

        for (Book book : this.booksMap.values()){
            c.gridy+=2;
            //this part displays the book name, ID, and all related info
            JLabel bookStatusLabel= new JLabel(book.getBookDetails());
            bookStatusLabel.setOpaque(true); // Make label opaque to show background color
            bookStatusLabel.setBackground(transparentDark);
            bookStatusLabel.setForeground(Color.WHITE); // Set font color to white
            statusPanel.add(bookStatusLabel,c);
            
        }

        statusPanel.setSize(getPreferredSize());
        statusFrame.setTitle("Libray System Status report");
        statusFrame.setSize(getPreferredSize());
        statusFrame.add(statusPanel);
        statusFrame.pack();
        statusFrame.setVisible(true);
        statusFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private Integer generateID(){
        ++this.ID;
        return this.ID;
    }

    private String getCategoryCount(){
        return this.categoryCountMap.toString();
    }


   /*  private String getBooksDetails(){
        String booksDetails="\n";

        for (Book book : this.booksMap.values()){
            //this part displays the book name, ID, and all related info
            booksDetails+= book.getBookStatus()+"\n";
        }
        return booksDetails;

    } */

    }