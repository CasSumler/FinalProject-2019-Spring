package models;

import javax.persistence.Id;

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
}
