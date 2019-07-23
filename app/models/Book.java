package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book
{
    @Id
    private int bookId;
    private String bookName;
    private String ISBN;
    private int authorId;
    private int bookStatusId;
    private int bookTypeId;
    private int genreId;

    public int getBookId()
    {
        return bookId;
    }

    public String getBookName()
    {
        return bookName;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public int getAuthorId()
    {
        return authorId;
    }

    public int getBookStatusId()
    {
        return bookStatusId;
    }

    public int getBookTypeId()
    {
        return bookTypeId;
    }

    public int getGenreId()
    {
        return genreId;
    }

    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public void setAuthorId(int authorId)
    {
        this.authorId = authorId;
    }

    public void setBookStatusId(int bookStatusId)
    {
        this.bookStatusId = bookStatusId;
    }

    public void setBookTypeId(int bookTypeId)
    {
        this.bookTypeId = bookTypeId;
    }

    public void setGenreId(int genreId)
    {
        this.genreId = genreId;
    }
}
