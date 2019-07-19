package models;

import javax.persistence.Id;

public class Book
{
    @Id
    private int bookId;
    private String bookName;
    private byte[] image;
    private String ISBN;
    private int authorId;
    private int bookStatusId;
    private int bookTypeId;
    private int genreId;

    public int getBookId()
    {
        return bookId;
    }

    public String getBookName()
    {
        return bookName;
    }

    public byte[] getImage()
    {
        return image;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public int getAuthorId()
    {
        return authorId;
    }

    public int getBookStatusId()
    {
        return bookStatusId;
    }

    public int getBookTypeId()
    {
        return bookTypeId;
    }

    public int getGenreId()
    {
        return genreId;
    }
}
