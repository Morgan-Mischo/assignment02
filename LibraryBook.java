package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a library book where the holderID and due date can be a variety of types
 * @author Morgan Mischo and Casey Rand
 *
 * @param <Type>
 */

public class LibraryBookGeneric<Type> extends Book
{
    private Type holderID = null; 
    
    private GregorianCalendar dueDate = null; 
    
    public LibraryBookGeneric(long isbn, String author, String title)
    {
        super(isbn, author, title);
    }
    
    public Type getHolder ()
    {
       return this.holderID;  
    }
      
    public GregorianCalendar getDueDate ()
    {
        return this.dueDate; 
    }
    
    //If checking book in, return to default state.
    public void checkBookIn ()
    {
        this.holderID = null; 
        this.dueDate = null; 
    }
    
    //If checking book out, new holderName and dueDate need to be set.
    public void checkBookOut (Type newID, GregorianCalendar newDate)
    {
       this.holderID = newID;
       this.dueDate = newDate; 
    }
}
