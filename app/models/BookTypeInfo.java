package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookTypeInfo
{
    @Id
    private int bookTypeId;
    private String bookTypeName;
    private String bookName;
    private String firstName;
    private String lastName;
    private String bookStatusName;

    public BookTypeInfo(int bookTypeId, String bookTypeName, String bookName, String firstName, String lastName, String bookStatusName)
    {
        this.bookTypeId = bookTypeId;
        this.bookTypeName = bookTypeName;
        this.bookName = bookName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookStatusName = bookStatusName;
    }

    public int getBookTypeId()
    {
        return bookTypeId;
    }

    public String getBookTypeName()
    {
        return bookTypeName;
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

    public String getBookStatusName()
    {
        return bookStatusName;
    }
}
