package models;

import java.util.Objects;

import interfaces.DatabaseManagement;
import org.sql2o.*;
import java.util.List;

public class Animal implements DatabaseManagement {
    public String name;
    public int id;
    public String type;
    public static final String ANIMAL_TYPE = "animal";

    public Animal(String name) {
        this.name=name;
        this.type=ANIMAL_TYPE;

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
        try(Connection conn = DB.sql2o.open()) {
            String sql = "INSERT INTO animals(name,type) VALUES(:name,:type)";
            this.id= (int) conn.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void delete() {
        try (Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM animals WHERE id=:id;";
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

    public  static List<Animal> all() {
        String sql = "SELECT * FROM animals WHERE type=:type";
        try (Connection conn = DB.sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("type","animal")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }
    public static Animal findById(int id){
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM  animals WHERE id=:id AND type=:type";
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .addParameter("type","animal")
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
        }
    }


}
