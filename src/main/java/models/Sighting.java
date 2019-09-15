package models;

import org.sql2o.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Sighting{
   private int id;
   private int animalId;
   private  String location;
   private int rangerId;
   private Timestamp timeRecorded;



    public Sighting(int animalId, String location, int rangerId) {
        this.animalId= animalId;
        this.location=location;
        this.rangerId = rangerId;

    }

    @Override
    public boolean equals(Object otherSighting) {
        if (this == otherSighting) return true;
        if (otherSighting == null || getClass() != otherSighting.getClass()) return false;
        Sighting sighting = (Sighting) otherSighting;
        return Objects.equals(animalId, sighting.animalId) &&
                Objects.equals(location, sighting.location)&&
                Objects.equals(rangerId,sighting.rangerId);
    }



    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sighting (animalId, location, rangerId) VALUES (:animalId, :location, :rangerId)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("animalId",this.animalId)
                    .addParameter("location",this.location)
                    .addParameter("rangerId",this.rangerId)
                    .executeUpdate()
                    .getKey();


        }
    }

    public static List<Sighting> all(){
        String sql = "SELECT * FROM sighting";try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sighting where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

    public int getId() {
        return id;
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getLocation() {
        return location;
    }

    public int getRangerId() {
        return rangerId;
    }

    public Timestamp getTimeRecorded() {
        return timeRecorded;
    }
}
