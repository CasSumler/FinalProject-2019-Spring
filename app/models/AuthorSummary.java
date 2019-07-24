package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthorSummary
{
    @Id
    private int authorId;
    private String lastName;
    private String firstName;
    private long totalBookCount;

    public AuthorSummary(int authorId, String lastName, String firstName, long totalBookCount)
    {
        this.authorId = authorId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.totalBookCount = totalBookCount;
    }

    public int getAuthorId()
    {
        return authorId;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public long getTotalBookCount()
    {
        return totalBookCount;
    }
}
