package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookStatusRepository
{
    private JPAApi jpaApi;

    @Inject
    public BookStatusRepository(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public List<BookStatus> getList()       //gets list of statuses for dropdowns
    {
        String sql = "SELECT bs FROM BookStatus bs ORDER BY bs.bookStatusId";
        TypedQuery<BookStatus> query = jpaApi.em().createQuery(sql, BookStatus.class);
        return query.getResultList();
    }

    public List<BookStatusSummary> getBookStatusSummary()       //gets # of books for each status as a list
    {
        String sql = "SELECT NEW BookStatusSummary(bs.bookStatusId, bs.bookStatusName, COUNT(*) AS totalStatusCount) " +
                "FROM BookStatus bs " +
                "JOIN Book b ON b.bookStatusId = bs.bookStatusId " +
                "GROUP BY bs.bookStatusId, bs.bookStatusName " +
                "ORDER BY totalStatusCount";
        TypedQuery<BookStatusSummary> query = jpaApi.em().createQuery(sql, BookStatusSummary.class);
        return query.getResultList();
    }

    public List<BookStatusInfo> getStatusList(int bookStatusId)     //gets info based on each status
    {
        String sql = "SELECT NEW BookStatusInfo(bs.bookStatusId, bs.bookStatusName, b.bookName, a.firstName, a.lastName, bt.bookTypeName) " +
                "FROM Book b " +
                "JOIN BookStatus bs ON bs.bookStatusId = b.bookStatusId " +
                "JOIN Author a ON a.authorId = b.authorId " +
                "JOIN BookType bt ON bt.bookTypeId = b.bookTypeId " +
                "WHERE bs.bookStatusId = :bookStatusId " +
                "ORDER BY b.bookName";
        TypedQuery<BookStatusInfo> query = jpaApi.em().createQuery(sql, BookStatusInfo.class);
        query.setParameter("bookStatusId", bookStatusId);
        return query.getResultList();
    }

    public BookStatus get(int bookStatusId)     //gets a book status
    {
        String sql = "SELECT bs FROM BookStatus bs WHERE bookStatusId = :bookStatusId";
        TypedQuery<BookStatus> query = jpaApi.em().createQuery(sql, BookStatus.class);
        query.setParameter("bookStatusId", bookStatusId);
        return query.getSingleResult();
    }
}
