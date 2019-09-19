package models;
import interfaces.DatabaseManagement;
import org.sql2o.*;
import java.util.List;

public class EndangeredAnimal extends Animal implements DatabaseManagement {
    public String age;
    public String health;


    public static final String ANIMAL_TYPE = "endangered";
    public static final String ADULT = "adult";
    public static final String YOUNG = "youth";
    public static final String NEWBORN = "newborn";
    public static final String HEALTHY = "healthy";
    public static final String AVERAGE = "okay";
    public static final String ILL = "ill";

    EndangeredAnimal(String name, String age, String health){
        super(name);
        this.name=name;
        this.age=age;
        this.health=health;
        this.type= ANIMAL_TYPE;

        if (name.equals("") || health.equals("") || age.equals("")){
            throw new IllegalArgumentException("Please enter all input fields.");
        }
    }

    public String getAge() {
        return age;
    }

    public String getHealth() {
        return health;
    }

    public static String getAnimalType() {
        return ANIMAL_TYPE;
    }


    @Override
    public boolean equals(Object otherEndangeredAnimal) {
        if (otherEndangeredAnimal instanceof EndangeredAnimal) {
            EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
            return (this.getName().equals(newEndangeredAnimal.getName()));
        }

        return false;
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (name, health, age, type) VALUES (:name, :health, :age, :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", name)
                    .addParameter("health", health)
                    .addParameter("age", age)
                    .addParameter("type", type)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<EndangeredAnimal> allEnd() {
        String sql = "SELECT * FROM endangered_animals ";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static EndangeredAnimal findEndangered(int id) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM endangered_animals WHERE id=:id AND type=:type";
            return (EndangeredAnimal) con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", "endangered")
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
        }
    }
@Override
    public void delete(int id) {
        try (Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM endangered_animals WHERE id=:id;";
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }
}
