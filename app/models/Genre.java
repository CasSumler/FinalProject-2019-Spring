package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genre
{
    @Id
    private int genreId;
    private String genreName;

    public int getGenreId()
    {
        return genreId;
    }

    public String getGenreName()
    {
        return genreName;
    }
}
