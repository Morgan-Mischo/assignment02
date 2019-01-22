package assign02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book
{
    private String holderName = null; 
    
    private GregorianCalendar dueDate = null; 
    
    public LibraryBook(long isbn, String author, String title)
    {
        super(isbn, author, title);
    }
    
    public String getHolder ()
    {
       return this.holderName;  
    }
      
    public GregorianCalendar getDueDate ()
    {
        return this.dueDate; 
    }
    
    //If checking book in, return to default state.
    public void checkBookIn ()
    {
        this.holderName = null; 
        this.dueDate = null; 
    }
    
    //If checking book out, new holderName and dueDate need to be set.
    public void checkBookOut (String newName, GregorianCalendar newDate)
    {
       this.holderName = newName;
       this.dueDate = newDate; 
    }
}