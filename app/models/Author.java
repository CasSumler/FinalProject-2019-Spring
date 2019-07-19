package models;

import javax.persistence.Id;

public class Author
{
    @Id
    private int authorId;
    private String lastName;
    private String firstName;

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
}
