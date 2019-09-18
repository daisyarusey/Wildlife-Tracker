package models;

import org.sql2o.Connection;

import java.util.List;

public class Location {
    private int id;
    private String name;

    public Location(int id,String name){
        this.id=id;
        this.name=name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static List<Location> all() {
        String sql = "SELECT * FROM locations ";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Location.class);
        }
    }
}
