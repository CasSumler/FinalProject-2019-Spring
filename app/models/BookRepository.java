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

    public Book get(int bookId)
    {
        String sql = "SELECT b FROM Book b WHERE bookId = :bookId";
        TypedQuery<Book> query = jpaApi.em().createQuery(sql, Book.class);
        query.setParameter("bookId", bookId);
        return query.getSingleResult();
    }

    public List<Book> getList()
    {
        String sql = "SELECT b FROM Book b ORDER BY b.bookName";
        TypedQuery<Book> query = jpaApi.em().createQuery(sql, Book.class);
        return query.getResultList();
    }

    public List<BookDetail> getDetailList(String searchName)
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

    public void addBook(Book book)
    {
        jpaApi.em().persist(book);
    }
}
