package interfaces;


public interface DatabaseManagement {

    boolean equals(Object otherObject);

    void save();

    void delete(int id);
}

