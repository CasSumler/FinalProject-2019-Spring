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

    public List<Book> getList()
    {
        String sql = "SELECT b FROM Book b ORDER BY b.bookName";
        TypedQuery<Book> query = jpaApi.em().createQuery(sql, Book.class);
        return query.getResultList();
    }

    public List<BookDetail> getDetailList()
    {
        String sql = "SELECT NEW BookDetail(b.bookId, b.bookName, a.firstName, a.lastName, bs.bookStatusName) " +
                "FROM Book b " +
                "JOIN Author a ON b.authorId = a.authorId " +
                "JOIN BookStatus bs ON b.bookStatusId = bs.bookStatusId " +
                "ORDER BY b.bookName";
        TypedQuery<BookDetail> query = jpaApi.em().createQuery(sql, BookDetail.class);
        return query.getResultList();
    }
}
