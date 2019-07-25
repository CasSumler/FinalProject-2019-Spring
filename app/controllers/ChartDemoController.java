package controllers;

import models.BookStatusSummary;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class ChartDemoController extends Controller
{
    public Result getDemo()
    {
        return ok(views.html.ChartDemo.render());
    }
}
