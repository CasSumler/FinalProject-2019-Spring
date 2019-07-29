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

    public List<BookStatus> getList()
    {
        String sql = "SELECT bs FROM BookStatus bs ORDER BY bs.bookStatusId";
        TypedQuery<BookStatus> query = jpaApi.em().createQuery(sql, BookStatus.class);
        return query.getResultList();
    }

    public List<BookStatusSummary> getBookStatusSummary()
    {
        String sql = "SELECT NEW BookStatusSummary(bs.bookStatusId, bs.bookStatusName, COUNT(*) AS totalStatusCount) " +
                "FROM BookStatus bs " +
                "JOIN Book b ON b.bookStatusId = bs.bookStatusId " +
                "GROUP BY bs.bookStatusId, bs.bookStatusName " +
                "ORDER BY totalStatusCount";
        TypedQuery<BookStatusSummary> query = jpaApi.em().createQuery(sql, BookStatusSummary.class);
        return query.getResultList();
    }

    public List<BookStatusInfo> getStatusList(int bookStatusId)
    {
        String sql = "SELECT NEW BookStatusInfo(bs.bookStatusId, bs.bookStatusName, b.bookName, a.firstName, a.lastName) " +
                "FROM Book b " +
                "JOIN BookStatus bs ON bs.bookStatusId = b.bookStatusId " +
                "JOIN Author a ON a.authorId = b.authorId " +
                "WHERE bs.bookStatusId = :bookStatusId " +
                "ORDER BY b.bookName";
        TypedQuery<BookStatusInfo> query = jpaApi.em().createQuery(sql, BookStatusInfo.class);
        query.setParameter("bookStatusId", bookStatusId);
        return query.getResultList();
    }

    public BookStatus get(int bookStatusId)
    {
        String sql = "SELECT bs FROM BookStatus bs WHERE bookStatusId = :bookStatusId";
        TypedQuery<BookStatus> query = jpaApi.em().createQuery(sql, BookStatus.class);
        query.setParameter("bookStatusId", bookStatusId);
        return query.getSingleResult();
    }
}
