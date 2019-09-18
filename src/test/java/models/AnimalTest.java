package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sql2o.*;

import static org.junit.Assert.*;

public class AnimalTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String sqlAnimal = "DELETE FROM animals *;";
            con.createQuery(sqlAnimal).executeUpdate();
        }
    }
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void instantiatesCorrectly_true(){
        Animal animal =  setUpNewAnimal();
        assertEquals(true, animal instanceof Animal);
    }

    @Test
    public void Animal_instantiatesWithName_String() {
        Animal animal = setUpNewAnimal();
        assertEquals("Lion", animal.getName());
    }

    @Test
    public void save_CorrectlyIntoTheDatabase() {
        Animal animal =  setUpNewAnimal();
        animal.save();
        assertTrue(Animal.all().get(0).equals(animal));
    }
    @Test
    public void save_assignsIdToObject() {
        Animal animal = setUpNewAnimal();
        animal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(savedAnimal.getId(), animal.getId());
    }

    @Test
    public void findById_returnsAnimalWIthSameID_secondAnimal(){
        Animal animalOne = setUpNewAnimal();
        animalOne.save();
        Animal animalTwo = new Animal("Impalla");
        animalTwo.save();
        assertEquals(Animal.findById(animalTwo.getId()), animalTwo);
    }
    @Test
    public void equals_returnsTrueIfAnimalsAreSame(){
        Animal animalOne =  setUpNewAnimal();
        Animal animalTwo = new Animal("Lion");
        assertTrue(animalOne.equals(animalTwo));
    }
    @Test
    public void save_returnsTrueIfNamesAreTheSame(){
        Animal animal =  setUpNewAnimal();
        animal.save();
        assertEquals(Animal.all().get(0), animal);
    }
    @Test
    public void all_returnsAllInstancesOfAnimals_true(){
        Animal animalOne =  setUpNewAnimal();
        animalOne.save();
        Animal animalTwo = new Animal("Impala");
        animalTwo.save();
        assertEquals(Animal.all().get(0), animalOne);
        assertEquals(Animal.all().get(1), animalTwo);
    }


    @Test
    public void delete_deletesAnimal_true(){
        Animal animal =  setUpNewAnimal();
        animal.save();
        int animalId = animal.getId();
        animal.delete(animalId);
        assertEquals(null, Animal.findById(animalId));
    }

    public Animal setUpNewAnimal(){
        return new Animal("Lion");
    }
}
