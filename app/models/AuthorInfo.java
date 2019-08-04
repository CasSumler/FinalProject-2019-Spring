package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthorInfo
{
    @Id
    private int authorId;
    private String bookName;
    private String bookTypeName;
    private String bookStatusName;

    public AuthorInfo(int authorId, String bookName, String bookTypeName, String bookStatusName)
    {
        this.authorId = authorId;
        this.bookName = bookName;
        this.bookTypeName = bookTypeName;
        this.bookStatusName = bookStatusName;
    }

    public int getAuthorId()
    {
        return authorId;
    }

    public String getBookName()
    {
        return bookName;
    }

    public String getBookTypeName()
    {
        return bookTypeName;
    }

    public String getBookStatusName()
    {
        return bookStatusName;
    }
}
