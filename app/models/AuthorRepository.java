package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class AuthorRepository
{
    private JPAApi jpaApi;

    @Inject
    public AuthorRepository(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public List<Author> getList()
    {
        String sql = "SELECT a FROM Author a ORDER BY a.lastName";
        TypedQuery<Author> query = jpaApi.em().createQuery(sql, Author.class);
        return query.getResultList();
    }
}
