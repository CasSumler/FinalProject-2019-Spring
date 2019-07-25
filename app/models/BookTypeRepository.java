package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookTypeRepository
{
    private JPAApi jpaApi;

    @Inject
    public BookTypeRepository(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public List<BookType> getList()
    {
        String sql = "SELECT bt FROM BookType bt ORDER BY bt.bookTypeName";
        TypedQuery<BookType> query = jpaApi.em().createQuery(sql, BookType.class);
        return query.getResultList();
    }

    public List<BookTypeSummary> getBookTypeSummary()
    {
        String sql = "SELECT NEW BookTypeSummary(bt.bookTypeId, bt.bookTypeName, COUNT(*) AS totalTypeCount) " +
                "FROM BookType bt " +
                "JOIN Book b ON b.bookTypeId = bt.bookTypeId " +
                "GROUP BY bt.bookTypeId " +
                "ORDER BY bt.bookTypeId";
        TypedQuery<BookTypeSummary> query = jpaApi.em().createQuery(sql, BookTypeSummary.class);
        return query.getResultList();
    }
}
