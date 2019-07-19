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
        String sql = "SELECT b FROM Book ORDER BY bookName";
        TypedQuery query = jpaApi.em().createQuery(sql, Book.class);
        return query.getResultList();
    }
}
