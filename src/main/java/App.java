import models.Animal;
import models.Location;
import models.Ranger;
import models.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");

        },new HandlebarsTemplateEngine());

        //get new form to record a sighting
        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", Ranger.all());
            model.put("locations", Location.all());
            model.put("animals", Animal.all());
            model.put("animal",true);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        // post the sighting
        post("/animal/new", (req, res) -> {
            Map<String,Object>model = new HashMap<>();
            int  animal_id = Integer.parseInt(req.queryParams("animal_id"));
            String location = req.queryParams("location");
            int ranger_id = Integer.parseInt(req.queryParams("ranger_id"));
            Sighting newSighting =  new Sighting(animal_id,location,ranger_id);
            newSighting.save();

            return new ModelAndView(model,"sighting.hbs");
        }, new HandlebarsTemplateEngine());

        //get list of rangers
        get("/view/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", Ranger.all());
            return new ModelAndView(model, "ranger-table.hbs");
        }, new HandlebarsTemplateEngine());

        //get list of locations
        get("/view/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("locations", Location.all());
            return new ModelAndView(model, "location-list.hbs");
        }, new HandlebarsTemplateEngine());


    }



}
