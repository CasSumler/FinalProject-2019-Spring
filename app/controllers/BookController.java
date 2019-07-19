package controllers;

import models.*;
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

    @Inject
    public BookController(FormFactory formFactory, BookRepository bookRepository, AuthorRepository authorRepository,
                          BookStatusRepository bookStatusRepository)
    {
        this.formFactory = formFactory;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStatusRepository = bookStatusRepository;
    }

    @Transactional(readOnly = true)
    public Result getList()
    {
        List<BookDetail> books = bookRepository.getDetailList();

        return ok(views.html.Book.render(books));
    }

    @Transactional(readOnly = true)
    public Result getAddBook()
    {
        return ok(views.html.AddBook.render());
    }

    @Transactional(readOnly = true)
    public Result getEditBook()
    {
        return ok(views.html.EditBook.render());
    }
}
