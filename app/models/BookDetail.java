package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookDetail
{
    @Id
    private int bookId;
    private String bookName;
    private String lastName;
    private String firstName;
    private String bookStatusName;

    public BookDetail(int bookId, String bookName, String lastName, String firstName, String bookStatusName)
    {
        this.bookId = bookId;
        this.bookName = bookName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.bookStatusName = bookStatusName;
    }

    public int getBookId()
    {
        return bookId;
    }

    public String getBookName()
    {
        return bookName;
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
}
