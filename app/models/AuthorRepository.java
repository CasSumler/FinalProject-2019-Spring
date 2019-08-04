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
        String sql = "SELECT a FROM Author a ORDER BY a.firstName";
        TypedQuery<Author> query = jpaApi.em().createQuery(sql, Author.class);
        return query.getResultList();
    }

    public List<Author> getSearchList(String searchName)
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

        String sql = "SELECT a " +
                "FROM Author a " +
                "WHERE a.firstName LIKE :searchName OR a.lastName LIKE :searchName " +
                "ORDER BY a.firstName";
        TypedQuery<Author> query = jpaApi.em().createQuery(sql, Author.class);
        query.setParameter("searchName", searchValue);
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

    public Author get(int authorId)
    {
        String sql = "SELECT a FROM Author a WHERE authorId = :authorId";
        TypedQuery<Author> query = jpaApi.em().createQuery(sql, Author.class);
        query.setParameter("authorId", authorId);
        return query.getSingleResult();
    }

    public List<AuthorInfo> getSingleAuthorList(int authorId)
    {
        String sql = "SELECT NEW AuthorInfo(b.authorId, b.bookName, bt.bookTypeName, bs.bookStatusName) " +
                "FROM Book b " +
                "JOIN BookType bt on b.bookTypeId = bt.bookTypeId " +
                "JOIN BookStatus bs ON b.bookStatusId = bs.bookStatusId " +
                "WHERE b.authorId = :authorId " +
                "ORDER BY b.bookName";
        TypedQuery<AuthorInfo> query = jpaApi.em().createQuery(sql, AuthorInfo.class);
        query.setParameter("authorId", authorId);
        return query.getResultList();
    }
}
