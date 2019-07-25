package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GenreSummary
{
    @Id
    private int genreId;
    private String genreName;
    private long totalGenreCount;

    public GenreSummary(int genreId, String genreName, long totalGenreCount)
    {
        this.genreId = genreId;
        this.genreName = genreName;
        this.totalGenreCount = totalGenreCount;
    }

    public int getGenreId()
    {
        return genreId;
    }

    public String getGenreName()
    {
        return genreName;
    }

    public long getTotalGenreCount()
    {
        return totalGenreCount;
    }
}
