package models;

import org.sql2o.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Sighting{

    private int id;
   private int animal_id;
   private  String location;
   private int ranger_id;
   private Timestamp time_recorded;



    public Sighting(int animal_id, String location, int ranger_id) {
        this.animal_id= animal_id;
        this.location=location;
        this.ranger_id = ranger_id;


    }

    @Override
    public boolean equals(Object otherSighting) {
        if (this == otherSighting) return true;
        if (otherSighting == null || getClass() != otherSighting.getClass()) return false;
        Sighting sighting = (Sighting) otherSighting;
        return Objects.equals(animal_id, sighting.animal_id) &&
                Objects.equals(location, sighting.location)&&
                Objects.equals(ranger_id,sighting.ranger_id);
    }

public Animal getAnimal(){
    String sql = "SELECT * FROM animals WHERE id = :id";
    try(Connection con = DB.sql2o.open()){
        Animal animal = con.createQuery(sql)
                .addParameter("id",this.animal_id)
                .executeAndFetchFirst(Animal.class);
        return animal;
    }
}

    public void save(){
        try(Connection conn = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings(animal_id,location,ranger_id,time_recorded) VALUES(:animal_id,:location,:ranger_id,now())";
            this.id= (int) conn.createQuery(sql,true)
                    .addParameter("animal_id",animal_id)
                    .addParameter("location",location)
                    .addParameter("ranger_id",ranger_id)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all(){
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public String getLocation() {
        return location;
    }

    public int getRanger_id() {
        return ranger_id;
    }

    public Timestamp getTimeRecorded() {
        return time_recorded;
    }
}
