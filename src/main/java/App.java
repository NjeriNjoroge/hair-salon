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

//dislays the client form
      get("/client/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/client-form.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

//creates new stylist when form is submitted
        post("/stylist", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          String name = request.queryParams("name");
          String speciality = request.queryParams("speciality");
          Stylist newStylist = new Stylist(name, speciality);
          newStylist.save();
          model.put("template", "templates/success.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//creates new client
post("/client", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
  String name = request.queryParams("name");
  int phone = Integer.parseInt(request.queryParams("phone"));
  Client newClient = new Client(name, phone, stylist.getId());
  newClient.save();
  model.put("stylist", stylist);
  model.put("template", "templates/success.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//displays new client form
          get("/stylists/:id/clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));

            model.put("stylist", stylist);
            model.put("template", "templates/stylist-clients-form.vtl");
            return new ModelAndView(model, layout);
          }, new VelocityTemplateEngine());

//displays all clients
      get("/clients", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("clients", Client.all());
        model.put("template", "templates/clients.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

//client details page
    get("clients/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("client", client);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//adds client to stylist
  post("/clients", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
    String Cname = request.queryParams("name");
    int Cphone = Integer.parseInt(request.queryParams("phone"));
    Client newClient = new Client(Cname, Cphone, stylist.getId());
    newClient.save();
    model.put("stylist", stylist);
    model.put("template", "templates/stylist-clients-success.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

//route to get update form
  get("/styles/:style_id/clients/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
    Client client = Client.find(Integer.parseInt(request.params(":id")));
    model.put("stylist", stylist);
    model.put("client", client);
    model.put("template", "templates/client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

//route to process update form
post("/stylists/:stylist_id/clients/:id", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Client client = Client.find(Integer.parseInt(request.params(":id")));
  String name = request.queryParams("name");
  int phone = Integer.parseInt(request.queryParams("phone"));
  Stylist stylist = Stylist.find(client.getStylistId());
  client.update(name, phone);
  String url = String.format("/clients/%d", client.getId());
  response.redirect(url);
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//deleting clients from DB
post("/stylists/:stylist_id/clients/:id/delete", (request, response) -> {
Map<String, Object> model = new HashMap<String, Object>();
Client client = Client.find(Integer.parseInt(request.params("id")));
Stylist stylist = Stylist.find(client.getStylistId());
client.delete();
model.put("stylist", stylist);
model.put("template", "templates/stylist.vtl");
return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//deleting stylists from DB
post("/stylists/stylists/:id/delete", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
  stylist.delete();
  model.put("stylist", stylist);
  model.put("template", "templates/index.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());


post("/stylists/:id", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
  String name = request.queryParams("name");
  String speciality = request.queryParams("speciality");
  stylist.update(name, speciality);
  String url = String.format("/stylists/%d", stylist.getId());
  response.redirect(url);
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//deploying
ProcessBuilder process = new ProcessBuilder();
 Integer port;
 if (process.environment().get("PORT") != null) {
     port = Integer.parseInt(process.environment().get("PORT"));
 } else {
     port = 4567;
 }

setPort(port);

  }
}
