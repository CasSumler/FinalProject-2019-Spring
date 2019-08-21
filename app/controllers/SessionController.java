package controllers;

import javax.inject.Inject;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

public class SessionController extends Controller
{
    private FormFactory formFactory;

    @Inject
    public SessionController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }

    public Result getLogin()
    {
        return ok(views.html.Login.render());
    }
}
