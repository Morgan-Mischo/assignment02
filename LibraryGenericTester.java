package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for LibraryGeneric.
 * 
 * @author Erin Parker, Morgan Mischo, and Casey Rand
 * @version January 16, 2019
 */
public class LibraryGenericTester {
	
	private LibraryGeneric<String> nameLib;  // library that uses names to identify patrons (holders)
	private LibraryGeneric<PhoneNumber> phoneLib;  // library that uses phone numbers to identify patrons
	
	@BeforeEach
	void setUp() throws Exception {
		nameLib = new LibraryGeneric<String>();
		nameLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		nameLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		nameLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		phoneLib = new LibraryGeneric<PhoneNumber>();
		phoneLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		phoneLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		phoneLib.add(9780446580342L, "David Baldacci", "Simple Genius");		
	}
	
	@Test
	public void testNameLibCheckout() {
		String patron = "Jane Doe";
		assertTrue(nameLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(nameLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testNameLibLookup() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = nameLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}
	
	@Test
	public void testNameLibCheckin() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(nameLib.checkin(patron));
	}

	@Test
	public void testPhoneLibCheckout() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(phoneLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(phoneLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testPhoneLibLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = phoneLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testPhoneLibCheckin() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(phoneLib.checkin(patron));
	}
	
	//Our Tests
	
	@Test
	public void testLookUpIsbnPhone() {
		PhoneNumber expected = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, expected, 1, 1, 2008);
		
		PhoneNumber actual = phoneLib.lookup(9780330351690L);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLookUpIsbnPhoneFailure() {
		PhoneNumber phone1 = new PhoneNumber("801.555.1234");
		PhoneNumber phone2 = new PhoneNumber("801.333.2134");
		phoneLib.checkout(9780330351690L, phone1, 1, 1, 2008);
		
		PhoneNumber actual = phoneLib.lookup(9780330351690L);
		
		assertFalse(phone2.equals(actual));
	}
	
	
	@Test
	public void testLookUpIsbnName() {
		String expected = "Jane Doe";
		nameLib.checkout(9780330351690L, expected, 1, 1, 2008);
		
		String actual = nameLib.lookup(9780330351690L);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getOrderedByTitle_Functions()
	{
		ArrayList<LibraryBookGeneric<String>> expectedLib = new ArrayList<LibraryBookGeneric<String>>();
		expectedLib.add(new LibraryBookGeneric<String> (9780330351690L, "Jon Krakauer", "Into the Wild"));
		expectedLib.add(new LibraryBookGeneric<String> (9780446580342L, "David Baldacci", "Simple Genius"));
		expectedLib.add(new LibraryBookGeneric<String> (9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		ArrayList<LibraryBookGeneric<String>>expected = expectedLib;
		
		ArrayList<LibraryBookGeneric<String>>actual = nameLib.getOrderedByTitle();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getOrderedByTitle_Fails()
	{
		ArrayList<LibraryBookGeneric<String>> expectedLib = new ArrayList<LibraryBookGeneric<String>>();
		expectedLib.add(new LibraryBookGeneric<String> (9780330351690L, "Jon Krakauer", "Into the Wild"));
		expectedLib.add(new LibraryBookGeneric<String> (9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		expectedLib.add(new LibraryBookGeneric<String> (9780446580342L, "David Baldacci", "Simple Genius"));
		ArrayList<LibraryBookGeneric<String>>expected = expectedLib;
		
		ArrayList<LibraryBookGeneric<String>>actual = nameLib.getOrderedByTitle();
		
		assertFalse(expected == actual);	
	}
	
	@Test 
	public void getInventoryList_Functions ()
	{
		ArrayList<LibraryBookGeneric<String>> expectedLib = new ArrayList<LibraryBookGeneric<String>>();
		expectedLib.add(new LibraryBookGeneric<String> (9780330351690L, "Jon Krakauer", "Into the Wild"));
		expectedLib.add(new LibraryBookGeneric<String> (9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		expectedLib.add(new LibraryBookGeneric<String> (9780446580342L, "David Baldacci", "Simple Genius"));
		ArrayList<LibraryBookGeneric<String>>expected = expectedLib;
		
		ArrayList<LibraryBookGeneric<String>> actual = nameLib.getInventoryList(); 
		
		assertEquals(expected, actual); 
	}
	
	@Test
	public void getInventoryList_Fails ()
	{
		ArrayList<LibraryBookGeneric<String>> expectedLib = new ArrayList<LibraryBookGeneric<String>>();
		expectedLib.add(new LibraryBookGeneric<String> (9780330351690L, "Jon Krakauer", "Into the Wild"));
		expectedLib.add(new LibraryBookGeneric<String> (9780446580342L, "David Baldacci", "Simple Genius"));
		expectedLib.add(new LibraryBookGeneric<String> (9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		ArrayList<LibraryBookGeneric<String>>expected = expectedLib;
		
		ArrayList<LibraryBookGeneric<String>> actual = nameLib.getInventoryList(); 
		
		assertFalse(expected == actual); 
	}
}
