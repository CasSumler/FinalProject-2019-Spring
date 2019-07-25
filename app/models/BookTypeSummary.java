package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookTypeSummary
{
    @Id
    private int bookTypeId;
    private String bookTypeName;
    private long totalTypeCount;

    public BookTypeSummary(int bookTypeId, String bookTypeName, long totalTypeCount)
    {
        this.bookTypeId = bookTypeId;
        this.bookTypeName = bookTypeName;
        this.totalTypeCount = totalTypeCount;
    }

    public int getBookTypeId()
    {
        return bookTypeId;
    }

    public String getBookTypeName()
    {
        return bookTypeName;
    }

    public long getTotalTypeCount()
    {
        return totalTypeCount;
    }
}
