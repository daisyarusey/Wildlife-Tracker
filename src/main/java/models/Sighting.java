package models;

public class Sighting{
   private int id;
   private int animalId;
   private  String location;
   private String rangerName;

    public int getId() {
        return id;
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public Sighting(int animalId, String location, String rangerName) {
        this.animalId= animalId;
        this.location=location;
        this.rangerName = rangerName;

    }
}
