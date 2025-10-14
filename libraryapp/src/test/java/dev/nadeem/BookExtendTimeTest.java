package dev.nadeem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookExtendTimeTest {
    private Book book;

    @BeforeEach
    void setup(){
        book = new Book("Harry Potter", "Fantasy", "J.K Rowling");
    }

    @Test //1- Subtract 7 days when borrowed more than 7 days
    void extendTime_subtractsSevenDays_whenBorrowedMoreThanSeven() {
    
    for (int i = 0; i < 10; i++) book.advanceDay();

    // Act: Extend time
    book.extendTime();

    // Assert: 10 - 7 = 3
    assertEquals(3, book.getDaysBorrowed());
    }
    @Test // 2- Borrowed less than 7 days should  reset to 0
    void extendTime_lessThanSevenDays_resetsToZero() {
    // Arrange: Borrowed for 5 days
    for (int i = 0; i <= 5; i++) book.advanceDay();

    // Act: Extend time
    book.extendTime();

    // Assert: should reset to 0
    assertEquals(0, book.getDaysBorrowed());
    }
}
