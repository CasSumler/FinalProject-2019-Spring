package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookInfo
{
    @Id
    private int bookId;
    private String bookName;
    private String ISBN;
    private String lastName;
    private String firstName;
    private String bookStatusName;
    private String bookTypeName;
    private String genreName;

    public BookInfo(int bookId, String bookName, String ISBN, String lastName, String firstName, String bookStatusName, String bookTypeName, String genreName)
    {
        this.bookId = bookId;
        this.bookName = bookName;
        this.ISBN = ISBN;
        this.lastName = lastName;
        this.firstName = firstName;
        this.bookStatusName = bookStatusName;
        this.bookTypeName = bookTypeName;
        this.genreName = genreName;
    }

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

    public String getLastName()
    {
        return lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getBookStatusName()
    {
        return bookStatusName;
    }

    public String getBookTypeName()
    {
        return bookTypeName;
    }

    public String getGenreName()
    {
        return genreName;
    }
}
