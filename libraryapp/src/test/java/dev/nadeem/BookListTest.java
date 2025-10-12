package dev.nadeem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookListTest {
    private Library library;

    @BeforeEach
    void setUp() {
        // Fresh library for each test
        library = new Library();
    }

    @Test // 1- List is empty when no books are borrowed
    void borrowedListIsEmptyBeforeBorrowing() {
        // Ask for borrowed list (nothing borrowed yet)
        int borrowedCount = library.listBorrowedBooks(false).size();

        // Should be empty
        assertEquals(0, borrowedCount);
    }

    @Test // 2- Borrowed book appears correctly in the list
    void borrowedListShowsBorrowedBook() {
        // Borrow one book
        library.borrowBook("Harry Potter");

        // Get list of borrowed books
        var borrowed = library.listBorrowedBooks(false);

        // Expect exactly one item and it should be "Harry Potter"
        assertEquals(1, borrowed.size());
        assertEquals("Harry Potter", borrowed.get(0).getName());
    }


}
