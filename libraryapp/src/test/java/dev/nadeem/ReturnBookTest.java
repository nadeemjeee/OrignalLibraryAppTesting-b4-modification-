package dev.nadeem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReturnBookTest {
    private Library library;

    @BeforeEach
    void setUp() {
        // Each test starts with a fresh library
        library = new Library();
    }

    @Test // 1- No fee if book returned before 7 days
    void returningBookBeforeSevenDaysHasNoLateFee() {
        // Borrow a book
        library.borrowBook("Harry Potter");

        // Simulate returning after 3 days
        Book b = library.listBorrowedBooks(false).get(0);
        for (int i = 0; i < 3; i++) b.advanceDay();

        // Return the book and check the fee
        int fee = library.returnBook("Harry Potter");

        // Expected: no late fee
        assertEquals(0, fee);
    }
    @Test // 2- Fee should apply if book returned after 7 days
    void returningBookAfterSevenDaysHasLateFee() {
        // Borrow a book
        library.borrowBook("Harry Potter");

        // Simulate 10 days borrowed
        Book b = library.listBorrowedBooks(false).get(0);
        for (int i = 0; i < 10; i++) b.advanceDay();

        // Return the book
        int fee = library.returnBook("Harry Potter");

        // Expected: late by (10 - 7) days = 3 * 20 = 60 kr
        assertEquals(60, fee);

    }

    @Test // 3- Returned book should be removed from borrowed list
    void returningBookRemovesItFromBorrowedList() {
        // Borrow a book
        library.borrowBook("Harry Potter");
        assertEquals(1, library.listBorrowedBooks(false).size());

        // Return it
        library.returnBook("Harry Potter");

        // Borrowed list should now be empty
        assertEquals(0, library.listBorrowedBooks(false).size());
    }

    @Test // 4- Returned book should be added back to available stock
    void returningBookAddsItBackToStock() {
    int stockBefore = library.listAvailableBooks().size();

    library.borrowBook("Harry Potter");
    // optional: assert stock decreased by 1
    assertEquals(stockBefore - 1, library.listAvailableBooks().size());

    library.returnBook("Harry Potter");

    // after return, stock should be restored to the original
    int stockAfter = library.listAvailableBooks().size();
    assertEquals(stockBefore, stockAfter);
    }




}
