package test.com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

  @Test
  void isKidFriendlyEligible() {
    // Test 1: movie genre MovieGenre.HORROR -- false
    Bookmark bookmark = BookmarkManager.getInstance().createMovie(3000,
        "Citizen Kane",
        1941,
        new String[]{"Orson Welles", "Joseph Cotten"},
        new String[]{"Orson Welles"},
        MovieGenre.HORROR,
        8.5
    );
    boolean isKidFriendlyEligible = bookmark.isKidFriendlyEligible();
    assertFalse(isKidFriendlyEligible, "For movie genre MovieGenre.HORROR - isKidFriendlyEligible() must return false");

    // Test 2: movie genre MovieGenre.THRILLERS -- false
    bookmark = BookmarkManager.getInstance().createMovie(3000,
        "Citizen Kane",
        1941,
        new String[]{"Orson Welles", "Joseph Cotten"},
        new String[]{"Orson Welles"},
        MovieGenre.THRILLERS,
        8.5
    );
    isKidFriendlyEligible = bookmark.isKidFriendlyEligible();
    assertFalse(
        isKidFriendlyEligible,
        "For movie genre MovieGenre.THRILLERS - isKidFriendlyEligible() must return false"
    );

    // Test 3: movie genre is not MovieGenre.HORROR or MovieGenre.THRILLERS -- true
    bookmark = BookmarkManager.getInstance().createMovie(3000,
        "Citizen Kane",
        1941,
        new String[]{"Orson Welles", "Joseph Cotten"},
        new String[]{"Orson Welles"},
        MovieGenre.CLASSICS,
        8.5
    );
    isKidFriendlyEligible = bookmark.isKidFriendlyEligible();
    assertTrue(
        isKidFriendlyEligible,
        "For movie genre not MovieGenre.HORROR or MovieGenre.THRILLERS - isKidFriendlyEligible() must return TRUE"
    );

  }
}