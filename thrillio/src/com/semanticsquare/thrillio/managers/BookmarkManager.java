package com.semanticsquare.thrillio.managers;

import com.semanticsquare.thrillio.dao.BookmarkDao;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.Movie;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;
import com.semanticsquare.thrillio.util.HttpConnect;
import com.semanticsquare.thrillio.util.IOUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class BookmarkManager {
  private static BookmarkManager instance = new BookmarkManager();
  private static BookmarkDao dao = new BookmarkDao();

  private BookmarkManager() {
  }

  public static BookmarkManager getInstance() {
    return instance;
  }

  public Movie createMovie(
      long id,
      String title,
      int releaseYear,
      String[] cast,
      String[] directors,
      String genre,
      double imdbRating
  ) {
    Movie movie = new Movie();
    movie.setId(id);
    movie.setTitle(title);
    movie.setReleaseYear(releaseYear);
    movie.setCast(cast);
    movie.setDirectors(directors);
    movie.setGenre(genre);
    movie.setImdbRating(imdbRating);

    return movie;
  }

  public Book createBook(
      long id,
      String title,
      int publicationYear,
      String publisher,
      String[] authors,
      String genre,
      double amazonRating
  ) {
    Book book = new Book();
    book.setId(id);
    book.setTitle(title);
    book.setPublicationYear(publicationYear);
    book.setPublisher(publisher);
    book.setAuthors(authors);
    book.setGenre(genre);
    book.setAmazonRating(amazonRating);

    return book;
  }

  public WebLink createWebLink(
      long id, String title, String url, String host
  ) {
    WebLink webLink = new WebLink();
    webLink.setId(id);
    webLink.setTitle(title);
    webLink.setUrl(url);
    webLink.setHost(host);

    return webLink;
  }

  public Bookmark[][] getBookmarks() {
    return dao.getBookmarks();
  }

  public void saveUserBookmark(User user, Bookmark bookmark) {
    UserBookmark userBookmark = new UserBookmark();
    userBookmark.setUser(user);
    userBookmark.setBookmark(bookmark);

    if (bookmark instanceof WebLink) {
      try {
        String url = ((WebLink) bookmark).getUrl();
        if (!url.endsWith(".pdf")) {
          String webpage = HttpConnect.download(((WebLink) bookmark).getUrl());
          if (webpage != null) {
            IOUtil.write(webpage, bookmark.getId());
          }
        }
      } catch (MalformedURLException e) {
          e.printStackTrace();
      } catch (URISyntaxException e) {
          e.printStackTrace();
      }
    }
    dao.saveUserBookmark(userBookmark);
  }

  public void share(User user, Bookmark bookmark) {
    bookmark.setSharedBy(user);
    if (bookmark instanceof Book) {
      System.out.println(((Book) bookmark).getItemData());
    } else if (bookmark instanceof WebLink) {
      System.out.println(((WebLink) bookmark).getItemData());
    }
  }
}
