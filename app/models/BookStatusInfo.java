package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookStatusInfo
{
    @Id
    private int bookStatusId;
    private String bookStatusName;
    private String bookName;
    private String firstName;
    private String lastName;
    private String bookTypeName;

    public BookStatusInfo(int bookStatusId, String bookStatusName, String bookName, String firstName, String lastName, String bookTypeName)
    {
        this.bookStatusId = bookStatusId;
        this.bookStatusName = bookStatusName;
        this.bookName = bookName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookTypeName = bookTypeName;
    }

    public int getBookStatusId()
    {
        return bookStatusId;
    }

    public String getBookStatusName()
    {
        return bookStatusName;
    }

    public String getBookName()
    {
        return bookName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getBookTypeName()
    {
        return bookTypeName;
    }
}
