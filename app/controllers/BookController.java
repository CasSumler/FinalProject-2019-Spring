package controllers;

import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class BookController extends Controller
{
    private FormFactory formFactory;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BookStatusRepository bookStatusRepository;
    private BookTypeRepository bookTypeRepository;
    private GenreRepository genreRepository;

    @Inject
    public BookController(FormFactory formFactory, BookRepository bookRepository, AuthorRepository authorRepository,
                          BookStatusRepository bookStatusRepository, BookTypeRepository bookTypeRepository,
                          GenreRepository genreRepository)
    {
        this.formFactory = formFactory;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStatusRepository = bookStatusRepository;
        this.bookTypeRepository = bookTypeRepository;
        this.genreRepository = genreRepository;
    }

    public Result getHomePage()         //gets home page
    {
        return ok(views.html.HomePage.render());
    }

    @Transactional(readOnly = true)
    public Result getList()             //gets main list of books in library
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String searchName = form.get("searchName");

        List<BookDetail> books = bookRepository.getDetailList(searchName);

        return ok(views.html.Book.render(books));
    }

    @Transactional(readOnly = true)
    public Result getBookInfo(int bookId)   //gets all info related to each book
    {
        BookInfo bookInfo = bookRepository.getInfo(bookId);

        return ok(views.html.DisplayBook.render(bookInfo));
    }

    @Transactional
    public Result postDeleteBook(int bookId)    //deletes one book
    {
        Book book = bookRepository.get(bookId);
        bookRepository.deleteBook(book);

        return redirect(routes.BookController.getList());
    }

    @Transactional(readOnly = true)
    public Result getAddBook()              //gets add book page
    {
        Book book = new Book();
        List<Author> authors = authorRepository.getList();
        List<BookType> bookTypes = bookTypeRepository.getList();
        List<Genre> genres = genreRepository.getList();
        List<BookStatus> bookStatuses = bookStatusRepository.getList();

        return ok(views.html.AddBook.render(book, authors, bookTypes, genres, bookStatuses));
    }

    @Transactional
    public Result postAddBook()         //adds new book w/ all info into database
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        Book book = new Book();
        String bookName = form.get("bookName");
        String ISBN = form.get("ISBN");
        int authorId = Integer.parseInt(form.get("authorId"));
        int bookTypeId = Integer.parseInt(form.get("bookTypeId"));
        int genreId = Integer.parseInt(form.get("genreId"));
        int bookStatusId = Integer.parseInt(form.get("bookStatusId"));

        book.setBookName(bookName);
        book.setISBN(ISBN);
        book.setAuthorId(authorId);
        book.setBookTypeId(bookTypeId);
        book.setGenreId(genreId);
        book.setBookStatusId(bookStatusId);

        bookRepository.addBook(book);

        return redirect(routes.BookController.getList());
    }

    @Transactional(readOnly = true)
    public Result getEditBook(int bookId)           //gets edit book page
    {
        Book book = bookRepository.get(bookId);
        List<Author> authors = authorRepository.getList();
        List<BookStatus> bookStatuses = bookStatusRepository.getList();
        List<BookType> bookTypes = bookTypeRepository.getList();
        List<Genre> genres = genreRepository.getList();

        return ok(views.html.EditBook.render(book, bookTypes, genres, bookStatuses, authors));
    }

    @Transactional
    public Result postEditBook(int bookId)          //saves changes associated with a book
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String bookName = form.get("bookName");
        String ISBN = form.get("ISBN");
        int bookStatusId = Integer.parseInt(form.get("bookStatusId"));
        int bookTypeId = Integer.parseInt(form.get("bookTypeId"));
        int genreId = Integer.parseInt(form.get("genreId"));
        int authorId = Integer.parseInt(form.get("authorName"));

        Book book = bookRepository.get(bookId);

        book.setBookName(bookName);
        book.setISBN(ISBN);
        book.setBookStatusId(bookStatusId);
        book.setBookTypeId(bookTypeId);
        book.setGenreId(genreId);
        book.setAuthorId(authorId);

        return redirect(routes.BookController.getList());
    }

    @Transactional(readOnly = true)
    public Result getAuthorList()           //gets list of all authors in database
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String searchName = form.get("searchName");

        List<Author> authors = authorRepository.getSearchList(searchName);
        return ok(views.html.AllAuthors.render(authors));
    }

    @Transactional(readOnly = true)
    public Result getAuthorSummaryList()        //gets all authors with books in database
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String searchName = form.get("searchName");

        List<AuthorSummary> authorSummaries = authorRepository.getDetailList(searchName);

        return ok(views.html.Author.render(authorSummaries));
    }

    @Transactional(readOnly = true)
    public Result getAddAuthor()        //gets add author page
    {
        List<Author> authors = authorRepository.getList();

        return ok(views.html.AddAuthor.render(authors));
    }

    @Transactional
    public Result postAddAuthor()       //adds an author to database
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        Author author = new Author();
        String firstName = form.get("firstName");
        String lastName = form.get("lastName");
        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorRepository.add(author);

        return redirect(routes.BookController.getAuthorList());
    }

    @Transactional(readOnly = true)
    public Result getStatusList()       //gets list of all statuses
    {
        List<BookStatus> bookStatuses = bookStatusRepository.getList();

        return ok(views.html.BookStatus.render(bookStatuses));
    }

    @Transactional(readOnly = true)
    public Result getTypeList()         //gets list of all book categories
    {
        List<BookType> bookTypes = bookTypeRepository.getList();

        return ok(views.html.BookType.render(bookTypes));
    }

    @Transactional(readOnly = true)
    public Result getGenreList()        //gets list of all genres
    {
        List<Genre> genres = genreRepository.getList();

        return ok(views.html.Genre.render(genres));
    }

    @Transactional(readOnly = true)
    public Result getAddGenre()     //gets add genre page
    {
        List<Genre> genres = genreRepository.getList();

        return ok(views.html.AddGenre.render(genres));
    }

    @Transactional
    public Result postAddGenre()    //adds new genre to database
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        Genre genre = new Genre();
        String genreName = form.get("genreName");
        genre.setGenreName(genreName);
        genreRepository.add(genre);

        return redirect(routes.BookController.getGenreList());
    }

    @Transactional
    public Result getBookStatusReport()     //gets status chart page
    {
        List<BookStatusSummary> bookStatusSummaries = bookStatusRepository.getBookStatusSummary();

        String dataPoints = bookStatusSummaries.stream().map(bookStatusSummary -> Long.toString(bookStatusSummary.getTotalStatusCount()))
                .collect(Collectors.joining("|"));

        String dataLabels = bookStatusSummaries.stream().map(bookStatusSummary -> bookStatusSummary.getBookStatusName())
                .collect(Collectors.joining("|"));

        return ok(views.html.BookStatusChart.render(bookStatusSummaries, dataPoints, dataLabels));
    }

    @Transactional
    public Result getBookTypeReport()       //gets category chart page
    {
        List<BookTypeSummary> bookTypeSummaries = bookTypeRepository.getBookTypeSummary();

        String dataPoints = bookTypeSummaries.stream().map(bookTypeSummary -> Long.toString(bookTypeSummary.getTotalTypeCount()))
                .collect(Collectors.joining("|"));

        String dataLabels = bookTypeSummaries.stream().map(bookTypeSummary -> bookTypeSummary.getBookTypeName())
                .collect(Collectors.joining("|"));

        return ok(views.html.BookTypeChart.render(bookTypeSummaries, dataPoints, dataLabels));
    }

    @Transactional
    public Result getGenreReport()      //gets genre chart page
    {
        List<GenreSummary> genreSummaries = genreRepository.getGenreSummary();

        String dataPoints = genreSummaries.stream().map(genreSummary -> Long.toString(genreSummary.getTotalGenreCount()))
                .collect(Collectors.joining("|"));

        String dataLabels = genreSummaries.stream().map(genreSummary -> genreSummary.getGenreName())
                .collect(Collectors.joining("|"));

        return ok(views.html.GenreChart.render(genreSummaries, dataPoints, dataLabels));
    }

    @Transactional(readOnly = true)
    public Result getEditAuthor(int authorId)       //gets edit author page
    {
        Author author = authorRepository.get(authorId);

        return ok(views.html.EditAuthor.render(author));
    }

    @Transactional
    public Result postEditAuthor(int authorId)      //saves changes made to author name
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String firstName = form.get("firstName");
        String lastName = form.get("lastName");

        Author author = authorRepository.get(authorId);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return redirect(routes.BookController.getAuthorList());
    }

    @Transactional(readOnly = true)
    public Result getEditGenre(int genreId)     //gets edit genre page
    {
        Genre genre = genreRepository.get(genreId);

        return ok(views.html.EditGenre.render(genre));
    }

    @Transactional
    public Result postEditGenre(int genreId)        //saves changes made to genre
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String genreName = form.get("genreName");

        Genre genre = genreRepository.get(genreId);
        genre.setGenreName(genreName);
        return redirect(routes.BookController.getGenreList());
    }

    @Transactional(readOnly = true)
    public Result getAddType()      //gets category list page
    {
        List<BookType> bookTypes = bookTypeRepository.getList();

        return ok(views.html.AddBookType.render(bookTypes));
    }

    @Transactional
    public Result postAddType()     //adds new category to database
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        BookType bookType = new BookType();
        String bookTypeName = form.get("bookTypeName");
        bookType.setBookTypeName(bookTypeName);
        bookTypeRepository.add(bookType);

        return redirect(routes.BookController.getTypeList());
    }

    @Transactional(readOnly = true)
    public Result getEditType(int bookTypeId)       //gets edit category page
    {
        BookType bookType = bookTypeRepository.get(bookTypeId);

        return ok(views.html.EditBookType.render(bookType));
    }

    @Transactional
    public Result postEditType(int bookTypeId)      //saves changes made to category name
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String bookTypeName = form.get("bookTypeName");

        BookType bookType = bookTypeRepository.get(bookTypeId);
        bookType.setBookTypeName(bookTypeName);

        return redirect(routes.BookController.getTypeList());
    }

    @Transactional(readOnly = true)
    public Result getStatusesList(int bookStatusId)     //gets every book listed under a selected status
    {
        List<BookStatusInfo> bookStatusInfos = bookStatusRepository.getStatusList(bookStatusId);
        BookStatus bookStatus = bookStatusRepository.get(bookStatusId);

        return ok(views.html.StatusList.render(bookStatusInfos, bookStatus));
    }

    @Transactional(readOnly = true)
    public Result getTypesList(int bookTypeId)      //gets every book listed under a selected category
    {
        List<BookTypeInfo> bookTypeInfos = bookTypeRepository.getTypeList(bookTypeId);
        BookType bookType = bookTypeRepository.get(bookTypeId);

        return ok(views.html.TypeList.render(bookTypeInfos, bookType));
    }

    @Transactional(readOnly = true)
    public Result getGenresList(int genreId)        //gets every book listed under a selected genre
    {
        List<GenreInfo> genreInfos = genreRepository.getGenreList(genreId);
        Genre genre = genreRepository.get(genreId);

        return ok(views.html.GenreList.render(genreInfos, genre));
    }

    @Transactional(readOnly = true)
    public Result getAuthorsList(int authorId)      //gets every book listed under a selected author
    {
        List<AuthorInfo> authorInfos = authorRepository.getSingleAuthorList(authorId);
        Author author = authorRepository.get(authorId);

        return ok(views.html.AuthorList.render(authorInfos, author));
    }
}
