package controllers;

import models.Genre;
import models.GenreRepository;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class GenreController extends Controller
{
    private FormFactory formFactory;
    private GenreRepository genreRepository;

    @Inject
    public GenreController(FormFactory formFactory, GenreRepository genreRepository)
    {
        this.formFactory = formFactory;
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public Result getList()
    {
        List<Genre> genres = genreRepository.getList();

        return ok(views.html.Genre.render(genres));
    }
}
