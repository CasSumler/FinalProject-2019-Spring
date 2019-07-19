package controllers;

import models.Book;
import models.BookRepository;
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

    @Inject
    public BookController(FormFactory formFactory, BookRepository bookRepository)
    {
        this.formFactory = formFactory;
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public Result getList()
    {
        return ok(views.html.Book.render());
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
