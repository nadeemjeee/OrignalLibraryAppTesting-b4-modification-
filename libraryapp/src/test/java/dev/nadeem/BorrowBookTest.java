package dev.nadeem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BorrowBookTest {
    private Library library;

    @BeforeEach
    void setUp() {
        // Fresh Library before each test so tests don't affect each other
        library = new Library();
    }

    @Test // 1 - Borrow adds only one copy of the title
   
    void borrowBook_addsExactlyOneCopyOfRequestedTitle() {
        library.borrowBook("Harry Potter");
        int borrowedCount = library.listBorrowedBooks(false).size();
        assertEquals(1, borrowedCount);
        
    }

    @Test // 2 - Stock decreases when a book is borrowed
    void borrowBook_removesOneCopyFromAvailableStock() {
        int stockBefore = library.listAvailableBooks().size();
        library.borrowBook("Harry Potter");
        int stockAfter = library.listAvailableBooks().size();
        assertEquals(stockBefore - 1, stockAfter);

    }

     @Test // 3 - The borrowed book reports borrowed = true
    void bookMarkAsBorrowedAfterBorrowing() {
        library.borrowBook("Harry Potter");
        Book borrowed = library.listBorrowedBooks(false).get(0);
        assertTrue(borrowed.isBorrowed());

    }

    @Test // 4 - Only one borrow allowed per day, regardless of title
    void cannotBorrowMoreThanOneBookPerDay() {
        library.borrowBook("Harry Potter");
        library.borrowBook("Hitchhiker's guide to the galaxy"); // same day
        int borrowedCount = library.listBorrowedBooks(false).size();
        assertEquals(1, borrowedCount);   
    }

     @Test // 5 - User cannot have more than 5 borrowed books at once
    void cannotBorrowMoreThanFiveBooksAtOnce() {
        Library library = new Library();

        library.borrowBook("Harry Potter");                       // 1
        library.advanceDay();
        library.borrowBook("Hitchhiker's guide to the galaxy");   // 2
        library.advanceDay();
        library.borrowBook("It ends with us");                    // 3
        library.advanceDay();
        library.borrowBook("Ondskan");                            // 4
        library.advanceDay();
        library.borrowBook("Tempelriddaren");                     // 5
        library.advanceDay();

        // Attempt a 6th book (any title); should be blocked by "max 5" rule
        library.borrowBook("Harry Potter"); // or any title in stock

        int borrowedCount = library.listBorrowedBooks(false).size();
        assertEquals(5, borrowedCount, "User should not have more than 5 borrowed books at once");
    }
    @Test // 6 - Borrowed book starts with 0 days borrowed
    void borrowedBookStartsWithZeroDays() {
        library.borrowBook("Harry Potter");
        Book borrowed = library.listBorrowedBooks(false).get(0);
        assertEquals(0, borrowed.getDaysBorrowed());
    }

}
