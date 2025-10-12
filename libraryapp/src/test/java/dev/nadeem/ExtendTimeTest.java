package dev.nadeem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExtendTimeTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library(); // fresh state per test
    }

    @Test // 1- Should reset daysBorrowed to 0 when borrowed < 7 days
    void extendTime_resetsDaysBorrowedToZero_whenBorrowedForFiveDays() {
        // borrow
        library.borrowBook("Harry Potter");

        // simulate 5 days on the borrowed copy
        Book b = library.listBorrowedBooks(false).get(0);
        for (int i = 0; i < 5; i++) b.advanceDay();

        // act: extend
        int newDays = library.extendTime("Harry Potter");

        // assert: must be 0 (reset), not negative
        assertEquals(0, newDays);
    }


}
