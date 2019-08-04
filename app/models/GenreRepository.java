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

    public List<GenreSummary> getGenreSummary()
    {
        String sql = "SELECT NEW GenreSummary(g.genreId, g.genreName, COUNT(*) as totalGenreCount) " +
                "FROM Genre g " +
                "JOIN Book b ON b.genreId = g.genreId " +
                "GROUP BY g.genreId " +
                "ORDER BY g.genreId";
        TypedQuery<GenreSummary> query = jpaApi.em().createQuery(sql, GenreSummary.class);
        return query.getResultList();
    }

    public Genre get(int genreId)
    {
        String sql = "SELECT g FROM Genre g WHERE genreId = :genreId";
        TypedQuery<Genre> query = jpaApi.em().createQuery(sql, Genre.class);
        query.setParameter("genreId", genreId);
        return query.getSingleResult();
    }

    public List<GenreInfo> getGenreList(int genreId)
    {
        String sql = "SELECT NEW GenreInfo(g.genreId, g.genreName, b.bookName, a.firstName, a.lastName, bs.bookStatusName) " +
                "FROM Book b " +
                "JOIN Genre g ON b.genreId = g.genreId " +
                "JOIN Author a ON b.authorId = a.authorId " +
                "JOIN BookStatus bs ON b.bookStatusId = bs.bookStatusId " +
                "WHERE g.genreId = :genreId " +
                "ORDER BY b.bookName";
        TypedQuery<GenreInfo> query = jpaApi.em().createQuery(sql, GenreInfo.class);
        query.setParameter("genreId", genreId);
        return query.getResultList();
    }

}
