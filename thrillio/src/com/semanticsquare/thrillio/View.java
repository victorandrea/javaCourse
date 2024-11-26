package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.partner.Sharable;

public class View {
  public static void browse(User user, Bookmark[][] bookmarks) {
    System.out.println("\n" + user.getEmail() + " is browsing items ...");
    int bookmarkCount = 0;
    for (Bookmark[] bookmarkList : bookmarks) {
      for (Bookmark bookmark : bookmarkList) {
        // User bookmarks:
        if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
          boolean isBookmarked = getBookmarkDecision(bookmark);
          if (isBookmarked) {
            bookmarkCount++;
            BookmarkController.getInstance().saveUserBookmark(user, bookmark);
            System.out.println("New Item Bookmarked -- " + bookmark);
          }
        }

        if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
          // Mark as kid-friendly:
          if (bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
            String kidFriendlyStatus = getKidFriendlyStatusDecision();
            if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
              BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
            }
          }

          // Sharing:
          if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Sharable) {
            boolean isShared = getShareDecision();
            if (isShared) {
              BookmarkController.getInstance().share(user, bookmark);
            }
          }
        }

      }
    }
  }

  //TODO: Below methods simulate user input. After IO, we take input via console
  private static boolean getShareDecision() {
    return Math.random() < 0.5 ? true: false;
  }

  private static String getKidFriendlyStatusDecision() {
    double randomVal = Math.random();
    return randomVal < 0.4 ? KidFriendlyStatus.APPROVED : (randomVal >= 0.4 && randomVal < 0.8) ?
        KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
  }

  private static boolean getBookmarkDecision(Bookmark bookmark) {
    return Math.random() < 0.5 ? true : false;
  }
}
