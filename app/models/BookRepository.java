package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookRepository
{
    private JPAApi jpaApi;

    @Inject
    public BookRepository(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public Book get(int bookId)     //gets all info about a book based on bookId
    {
        String sql = "SELECT b FROM Book b WHERE bookId = :bookId";
        TypedQuery<Book> query = jpaApi.em().createQuery(sql, Book.class);
        query.setParameter("bookId", bookId);
        return query.getSingleResult();
    }

    public BookInfo getInfo(int bookId)     //gets all book info (readable, not just FKs) to display on page
    {
        String sql = "SELECT NEW BookInfo(b.bookId, b.bookName, b.ISBN, a.firstName, a.lastName, bs.bookStatusName, bt.bookTypeName, g.genreName) " +
                "FROM Book b " +
                "JOIN Author a ON b.authorId = a.authorId " +
                "JOIN BookStatus bs ON b.bookStatusId = bs.bookStatusId " +
                "JOIN BookType bt ON b.bookTypeId = bt.bookTypeId " +
                "JOIN Genre g ON b.genreId = g.genreId " +
                "WHERE bookId = :bookId";
        TypedQuery<BookInfo> query = jpaApi.em().createQuery(sql, BookInfo.class);
        query.setParameter("bookId", bookId);
        return query.getSingleResult();
    }

    public List<Book> getList()     //gets simple list of books
    {
        String sql = "SELECT b FROM Book b ORDER BY b.bookName";
        TypedQuery<Book> query = jpaApi.em().createQuery(sql, Book.class);
        return query.getResultList();
    }

    public List<BookDetail> getDetailList(String searchName)    //gets main list of books
    {
        String searchValue = searchName;

        if(searchValue == null)
        {
            searchValue = "";
        }

        if(searchValue.length() <= 1)
        {
            searchValue = searchValue + "%";
        }
        else
        {
            searchValue = "%" + searchValue + "%";
        }

        String sql = "SELECT NEW BookDetail(b.bookId, b.bookName, a.firstName, a.lastName, bs.bookStatusName) " +
                "FROM Book b " +
                "JOIN Author a ON b.authorId = a.authorId " +
                "JOIN BookStatus bs ON b.bookStatusId = bs.bookStatusId " +
                "WHERE b.bookName LIKE :searchName " +
                "ORDER BY b.bookName";
        TypedQuery<BookDetail> query = jpaApi.em().createQuery(sql, BookDetail.class);
        query.setParameter("searchName", searchValue);
        return query.getResultList();
    }

    public void addBook(Book book)      //adds book to database
    {
        jpaApi.em().persist(book);
    }

    public void deleteBook(Book book)       //deletes book from database
    {
        jpaApi.em().remove(book);
    }
}
