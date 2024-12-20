package com.semanticsquare.thrillio.controllers;

import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;

public class BookmarkController {
  private static BookmarkController instance = new BookmarkController();

  private BookmarkController() {
  }

  public static BookmarkController getInstance() {
    return instance;
  }

  public void saveUserBookmark(User user, Bookmark bookmark) {
    BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
  }

  public void setKidFriendlyStatus(
      User user, String kidFriendlyStatus, Bookmark bookmark
  ) {
    bookmark.setKidFriendlyMarkedBy(user);
    bookmark.setKidFriendlyStatus(kidFriendlyStatus);
    System.out.println("Kid-friendly status: " + kidFriendlyStatus + ", Marked by: " + user.getEmail() + ", " + bookmark);
  }

  public void share(User user, Bookmark bookmark) {
    BookmarkManager.getInstance().share(user, bookmark);
  }
}
