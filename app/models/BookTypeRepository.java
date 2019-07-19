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
        String sql = "SELECT bt FROM BookType ORDER BY bt.bookTypeName";
        TypedQuery<BookType> query = jpaApi.em().createQuery(sql, BookType.class);
        return query.getResultList();

    }
}
