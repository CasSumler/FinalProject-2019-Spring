package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GenreInfo
{
    @Id
    private int genreId;
    private String genreName;
    private String bookName;
    private String firstName;
    private String lastName;
    private String bookStatusName;

    public GenreInfo(int genreId, String genreName, String bookName, String firstName, String lastName, String bookStatusName)
    {
        this.genreId = genreId;
        this.genreName = genreName;
        this.bookName = bookName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookStatusName = bookStatusName;
    }

    public int getGenreId()
    {
        return genreId;
    }

    public String getGenreName()
    {
        return genreName;
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
