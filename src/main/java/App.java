import models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        staticFileLocation("/public");

        get("/",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");

        },new HandlebarsTemplateEngine());

        //get new form to record a animal sighting
        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", Ranger.all());
            model.put("locations", Location.all());
            model.put("animals", Animal.all());
            model.put("animals",true);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        // post the sighting
        post("/animal/new", (req, res) -> {
            Map<String,Object>model = new HashMap<>();
            String name=req.queryParams("animal_name");
            String location = req.queryParams("location");
            int ranger_id = Integer.parseInt(req.queryParams("ranger_id"));
            Animal newAnimal= new Animal(name);
//            System.out.println(newAnimal.getId());
            Sighting newSighting =  new Sighting(newAnimal.getId(),location,ranger_id);
            newSighting.save();
            newAnimal.save();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //get new form to record an endangered sighting
        get("/endAnimal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("Healthy", EndangeredAnimal.HEALTHY);
            model.put("Ill", EndangeredAnimal.ILL);
            model.put("Okay", EndangeredAnimal.AVERAGE);
            model.put("Young", EndangeredAnimal.YOUNG);
            model.put("Adult", EndangeredAnimal.ADULT);
            model.put("Newborn", EndangeredAnimal.NEWBORN);
            model.put("rangers", Ranger.all());
            model.put("locations", Location.all());
            model.put("endangered_animals", EndangeredAnimal.all());
            model.put("endAnimals",true);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endAnimal/new", (req, res) -> {
            Map<String,Object>model = new HashMap<>();
            String location = req.queryParams("location");
            int ranger_id = Integer.parseInt(req.queryParams("ranger_id"));
            String name= req.queryParams("animal_name");
            String health=req.queryParams("health");
            String age =req.queryParams("age");

            EndangeredAnimal newAnimal= new EndangeredAnimal(name,health,age);
            newAnimal.save();
            Sighting newSighting =  new Sighting(newAnimal.getId(),location,ranger_id);
            newSighting.save();
            return new ModelAndView(model,"view.hbs");
        }, new HandlebarsTemplateEngine());

        //view sightings
        get("/view/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightings = Sighting.all();
            model.put("sightings",sightings);
            List<Animal> animals = Animal.all();
            model.put("animals",animals);
            List<EndangeredAnimal> endangeredAnimals=EndangeredAnimal.allEnd();
            model.put("endangered_animals",endangeredAnimals);
            List<Ranger> rangers = Ranger.all();
            model.put("rangers", rangers);

            return new ModelAndView(model, "view.hbs");
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
