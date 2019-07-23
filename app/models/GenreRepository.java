package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenreRepository
{
    private JPAApi jpaApi;

    @Inject
    public GenreRepository(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public List<Genre> getList()
    {
        String sql = "SELECT g FROM Genre g ORDER BY g.genreName";
        TypedQuery<Genre> query = jpaApi.em().createQuery(sql, Genre.class);
        return query.getResultList();
    }

    public void add(Genre genre)
    {
        jpaApi.em().persist(genre);
    }

}
