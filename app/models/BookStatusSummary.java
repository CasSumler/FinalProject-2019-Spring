package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookStatusSummary
{
    @Id
    private int bookStatusId;
    private String bookStatusName;
    private long totalStatusCount;

    public BookStatusSummary(int bookStatusId, String bookStatusName, long totalStatusCount)
    {
        this.bookStatusId = bookStatusId;
        this.bookStatusName = bookStatusName;
        this.totalStatusCount = totalStatusCount;
    }

    public int getBookStatusId()
    {
        return bookStatusId;
    }

    public String getBookStatusName()
    {
        return bookStatusName;
    }

    public long getTotalStatusCount()
    {
        return totalStatusCount;
    }
}
