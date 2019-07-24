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

    public void add(Author author)
    {
        jpaApi.em().persist(author);
    }

    public List<AuthorSummary> getDetailList(String searchName)
    {
        String searchValue = searchName;

        if(searchValue == null)
        {
            searchValue = "";
        }

        if(searchValue.length() <= 1)
        {
            searchValue = searchValue + "%";
        }
        else
        {
            searchValue = "%" + searchValue + "%";
        }

        String sql = "SELECT NEW AuthorSummary(a.authorId, a.firstName, a.lastName, COUNT(*) AS totalBookCount) " +
                "FROM Author a " +
                "JOIN Book b ON a.authorId = b.authorId " +
                "WHERE a.firstName LIKE :searchName OR a.lastName LIKE :searchName " +
                "GROUP BY a.authorId, a.firstName, a.lastName " +
                "ORDER BY a.firstName, a.lastName";
        TypedQuery<AuthorSummary> query = jpaApi.em().createQuery(sql, AuthorSummary.class);
        query.setParameter("searchName", searchValue);
        return query.getResultList();
    }


}
