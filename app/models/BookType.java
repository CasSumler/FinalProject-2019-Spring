package models;

import javax.persistence.Id;

public class BookType
{
    @Id
    private int bookTypeId;
    private String bookTypeName;

    public int getBookTypeId()
    {
        return bookTypeId;
    }

    public String getBookTypeName()
    {
        return bookTypeName;
    }
}
