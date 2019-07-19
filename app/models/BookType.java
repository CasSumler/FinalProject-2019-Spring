package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
