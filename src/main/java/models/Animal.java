package models;

import java.util.Objects;

import interfaces.DatabaseManagement;
import org.sql2o.*;
import java.util.List;

public class Animal implements DatabaseManagement {
    public String name;
    public int id;
    public String type;
    public static final String ANIMAL_TYPE = "non-endangered";

    public Animal(String name) {
        this.name=name;
        this.type=ANIMAL_TYPE;

    }
    public void setId(){
        this.id=id;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public static String getAnimalType() {
        return ANIMAL_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) &&
                Objects.equals(id, animal.id) &&
                Objects.equals(type, animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, type);
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type) VALUES (:name,:type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", name)
                    .addParameter("type", type)
                    .executeUpdate()
                    .getKey();
        }
    }


    public void delete(int id) {
        try (Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM animals WHERE id=:id;";
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

    public  static List<Animal> all() {
        String sql = "SELECT * FROM animals WHERE type='non-endangered'";
        try (Connection conn = DB.sql2o.open()){
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }
    public static Animal findById(int id){
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM  animals WHERE id=:id; ";
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
        }
    }


}
