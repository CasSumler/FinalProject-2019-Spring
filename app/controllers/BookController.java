package controllers;

import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

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

    public Result getHomePage()
    {
        return ok(views.html.HomePage.render());
    }

    @Transactional(readOnly = true)
    public Result getList()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String searchName = form.get("searchName");

        List<BookDetail> books = bookRepository.getDetailList(searchName);

        return ok(views.html.Book.render(books));
    }

    @Transactional(readOnly = true)
    public Result getBook(int bookId)
    {
        Book book = bookRepository.get(bookId);

        return ok(views.html.DisplayBook.render(book));
    }

    public Result getAddBook()
    {
        return ok(views.html.AddBook.render());
    }

    @Transactional
    public Result postAddBook()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        Book book = new Book();
        String bookName = form.get("bookName");
        book.setBookName(bookName);

        return redirect(routes.BookController.getList());
    }

    @Transactional(readOnly = true)
    public Result getEditBook(int bookId)
    {
        Book book = bookRepository.get(bookId);
        List<Author> authors = authorRepository.getList();
        List<BookStatus> bookStatuses = bookStatusRepository.getList();
        List<BookType> bookTypes = bookTypeRepository.getList();
        List<Genre> genres = genreRepository.getList();

        return ok(views.html.EditBook.render(book, bookTypes, genres, bookStatuses, authors));
    }

    @Transactional
    public Result postEditBook(int bookId)
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String bookName = form.get("bookName");
        String ISBN = form.get("ISBN");
        int bookStatusId = Integer.parseInt(form.get("bookStatusId"));
        int bookTypeId = Integer.parseInt(form.get("bookTypeId"));
        int genreId = Integer.parseInt(form.get("genreId"));


        Book book = bookRepository.get(bookId);

        book.setBookName(bookName);
        book.setISBN(ISBN);
        book.setBookStatusId(bookStatusId);
        book.setBookTypeId(bookTypeId);
        book.setGenreId(genreId);

        return redirect(routes.BookController.getList());
    }

    @Transactional(readOnly = true)
    public Result getAuthorList()
    {
        List<Author> authors = authorRepository.getList();

        return ok(views.html.Author.render(authors));
    }

    @Transactional(readOnly = true)
    public Result getStatusList()
    {
        List<BookStatus> bookStatuses = bookStatusRepository.getList();

        return ok(views.html.BookStatus.render(bookStatuses));
    }

    @Transactional(readOnly = true)
    public Result getTypeList()
    {
        List<BookType> bookTypes = bookTypeRepository.getList();

        return ok(views.html.BookType.render(bookTypes));
    }

    @Transactional(readOnly = true)
    public Result getGenreList()
    {
        List<Genre> genres = genreRepository.getList();

        return ok(views.html.Genre.render(genres));
    }

    public Result getAddGenre()
    {
        return ok(views.html.AddGenre.render());
    }

    @Transactional
    public Result postAddGenre()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        Genre genre = new Genre();
        String genreName = form.get("genreName");
        genre.setGenreName(genreName);
        genreRepository.add(genre);

        return redirect(routes.BookController.getGenreList());
    }
}
