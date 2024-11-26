package test.com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

  @Test
  void isKidFriendlyEligible() {
    // Test 1: BookGenre.PHILOSOPHY -- false
    Book book = BookmarkManager.getInstance().createBook(4000,
        "Walden",
        1854,
        "Wilder Publications",
        new String[]{"Henry David Thoreau"},
        BookGenre.PHILOSOPHY,
        4.3
    );
    boolean isKidFriendlyEligible = book.isKidFriendlyEligible();
    assertFalse(isKidFriendlyEligible,
        "For book genre BookGenre.PHILOSOPHY - isKidFriendlyEligible() must return false"
    );

    // Test 2: BookGenre.SELF_HELP -- false
    book = BookmarkManager.getInstance().createBook(4000,
        "Walden",
        1854,
        "Wilder Publications",
        new String[]{"Henry David Thoreau"},
        BookGenre.SELF_HELP,
        4.3
    );
    isKidFriendlyEligible = book.isKidFriendlyEligible();
    assertFalse(isKidFriendlyEligible,
        "For book genre BookGenre.SELF_HELP - isKidFriendlyEligible() must return false"
    );

    // Test 3: BookGenre not SELF_HELP or PHILOSOPHY -- true
    book = BookmarkManager.getInstance().createBook(4000,
        "Walden",
        1854,
        "Wilder Publications",
        new String[]{"Henry David Thoreau"},
        BookGenre.ART,
        4.3
    );
    isKidFriendlyEligible = book.isKidFriendlyEligible();
    assertTrue(isKidFriendlyEligible,
        "For book genre not PHILOSOPHY or SELF_HELP - isKidFriendlyEligible() must return false"
    );
  }
}