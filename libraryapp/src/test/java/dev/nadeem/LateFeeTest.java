package dev.nadeem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LateFeeTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("Harry Potter", "Fantasy", "J.K Rowling");
    }

    @Test //1 - No late fee before 7 days
    void checkLateFee_returnsZero_whenBorrowedLessThanSevenDays() {
        // Simulate borrowing for 5 days
        for (int i = 0; i < 5; i++) book.advanceDay();

        assertEquals(0, book.checkLateFee());
    }
    @Test // 2- Fee for days beyond seven
    
    void feeForDaysBeyondSeven() {
        for (int i = 0; i < 10; i++) book.advanceDay(); // 10 days
        assertEquals(60, book.checkLateFee()); // (10 - 7) * 20
    }
    @Test //3- Exactly 7 days is still free
    void noFee_whenBorrowedExactlySevenDays() {
        for (int i = 0; i < 7; i++) book.advanceDay();  // 7 days
        assertEquals(0, book.checkLateFee());
    }

}
