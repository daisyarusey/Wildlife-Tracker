package models;

import org.sql2o.Connection;

import java.util.List;

public class Ranger {
    public int id;
    public String name;
    public int budge_number;
    public int contact;

    public Ranger(int id, String name, int budge_number, int contact) {
        this.id = id;
        this.budge_number = budge_number;
        this.name = name;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBudge_number() {
        return budge_number;
    }

    public int getContact() {
        return contact;
    }

    public static List<Ranger> all() {
        String sql = "SELECT * FROM rangers ";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }

    public static Ranger find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers where id=:id";
            Ranger ranger = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }
}
