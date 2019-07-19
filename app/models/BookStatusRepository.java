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
        String sql = "SELECT bs FROM BookStatus bs ORDER BY bs.bookStatusName";
        TypedQuery<BookStatus> query = jpaApi.em().createQuery(sql, BookStatus.class);
        return query.getResultList();
    }
}
