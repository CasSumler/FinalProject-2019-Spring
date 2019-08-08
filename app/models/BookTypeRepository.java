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

    public List<BookType> getList()     //gets list of categories for dropdown and add page
    {
        String sql = "SELECT bt FROM BookType bt ORDER BY bt.bookTypeName";
        TypedQuery<BookType> query = jpaApi.em().createQuery(sql, BookType.class);
        return query.getResultList();
    }

    public List<BookTypeSummary> getBookTypeSummary()       //gets # of books in each category
    {
        String sql = "SELECT NEW BookTypeSummary(bt.bookTypeId, bt.bookTypeName, COUNT(*) AS totalTypeCount) " +
                "FROM BookType bt " +
                "JOIN Book b ON b.bookTypeId = bt.bookTypeId " +
                "GROUP BY bt.bookTypeId " +
                "ORDER BY bt.bookTypeId";
        TypedQuery<BookTypeSummary> query = jpaApi.em().createQuery(sql, BookTypeSummary.class);
        return query.getResultList();
    }

    public BookType get(int bookTypeId)     //gets category
    {
        String sql = "SELECT bt FROM BookType bt WHERE bookTypeId = :bookTypeId";
        TypedQuery<BookType> query = jpaApi.em().createQuery(sql, BookType.class);
        query.setParameter("bookTypeId", bookTypeId);
        return query.getSingleResult();
    }

    public void add(BookType bookType)      //adds new category to database
    {
        jpaApi.em().persist(bookType);
    }

    public List<BookTypeInfo> getTypeList(int bookTypeId)       //gets every book under each category
    {
        String sql = "SELECT NEW BookTypeInfo(bt.bookTypeId, bt.bookTypeName, b.bookName, a.firstName, a.lastName, bs.bookStatusName) " +
                "FROM Book b " +
                "JOIN BookType bt ON b.bookTypeId = bt.bookTypeId " +
                "JOIN Author a ON b.authorId = a.authorId " +
                "JOIN BookStatus bs ON b.bookStatusId = bs.bookStatusId " +
                "WHERE bt.bookTypeId = :bookTypeId " +
                "ORDER BY b.bookName";
        TypedQuery<BookTypeInfo> query = jpaApi.em().createQuery(sql, BookTypeInfo.class);
        query.setParameter("bookTypeId", bookTypeId);
        return query.getResultList();
    }
}
