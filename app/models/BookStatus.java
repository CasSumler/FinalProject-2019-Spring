package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookStatus
{
    @Id
    private int bookStatusId;
    private String bookStatusName;

    public int getBookStatusId()
    {
        return bookStatusId;
    }

    public String getBookStatusName()
    {
        return bookStatusName;
    }

    public void setBookStatusId(int bookStatusId)
    {
        this.bookStatusId = bookStatusId;
    }

    public void setBookStatusName(String bookStatusName)
    {
        this.bookStatusName = bookStatusName;
    }
}
