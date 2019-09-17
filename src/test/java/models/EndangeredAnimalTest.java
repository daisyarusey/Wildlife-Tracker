package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import  org.sql2o.*;

import java.util.Objects;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        try(Connection conn = DB.sql2o.open()){
            String endangeredAnimal = "DELETE FROM endangered_animals *;";
            conn.createQuery(endangeredAnimal).executeUpdate();
        }
    }
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void enAnimal_InstantiatesCorrectly_true(){
        EndangeredAnimal endangeredAnimal = setupNewEndangeredAnimal();
        assertEquals(true,endangeredAnimal instanceof EndangeredAnimal);
    }
    @Test
    public void enAnimal_InstantiatesWithNameCorrectly_String(){
        EndangeredAnimal endangeredAnimal = setupNewEndangeredAnimal();
        assertEquals("White Rhino",endangeredAnimal.getName());
    }
    @Test
    public void enAnimal_CorrectlyInstantiatesWithType() throws Exception{
        EndangeredAnimal endangeredAnimal = setupNewEndangeredAnimal();
        assertEquals("endangered",endangeredAnimal.getType());
    }

    @Test
    public void save_CorrectlyIntoTheDatabase() {
        EndangeredAnimal endangeredAnimal =  setupNewEndangeredAnimal();
        endangeredAnimal.save();
        assertTrue(EndangeredAnimal.allEnd().get(0).equals(endangeredAnimal));
    }
    @Test
    public void save_assignsIdToObject() {
        EndangeredAnimal testEndangeredAnimal =  setupNewEndangeredAnimal();
        testEndangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.allEnd().get(0);
        assertEquals(testEndangeredAnimal.getId(),savedEndangeredAnimal.getId() );
    }

    @Test
    public void enAnimal_FindsEndangeredAnimalWithTheSameId() throws Exception{
        EndangeredAnimal firstEndangered = setupNewEndangeredAnimal();
        firstEndangered.save();
        EndangeredAnimal secondEndangered = new EndangeredAnimal("Cheetah",EndangeredAnimal.ADULT,EndangeredAnimal.ILL);
        secondEndangered.save();
        assertEquals(EndangeredAnimal.findEndangered(secondEndangered.getId()),secondEndangered);
    }

//helper class
    public EndangeredAnimal setupNewEndangeredAnimal(){
        return new EndangeredAnimal("White Rhino",EndangeredAnimal.ADULT,EndangeredAnimal.ILL);
    }
}