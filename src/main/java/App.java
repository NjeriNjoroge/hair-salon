import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

//displays the index page when Spark is launched
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//lists all Stylists
      get("/stylists", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("stylists", Stylist.all());
        model.put("template", "templates/stylists.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

//displays Stylists details
        get("/stylists/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
          model.put("stylist", stylist);
          model.put("template", "templates/stylist.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//displays the Stylist form
      get("/stylist/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/stylist-form.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

//creates new stylist when form is submitted
        post("/stylist", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          String name = request.queryParams("name");
          String speciality = request.queryParams("speciality");
          Stylist newStylist = new Stylist(name, speciality);
          model.put("template", "templates/success.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//displays new client form
          get("/clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/client-form.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());
  }
}
