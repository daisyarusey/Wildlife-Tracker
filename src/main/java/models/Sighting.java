package models;

import java.sql.Timestamp;

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
