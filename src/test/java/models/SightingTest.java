package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sighting testSighting = new Sighting(2,"Zone A",2);
        assertEquals(true, testSighting instanceof Sighting);
    }
    @Test
    public void getAnimalId_sightingInstantiatesWithAnimalId_2() {
        Sighting testSighting = new Sighting(2,"Zone A",2);
        assertEquals(2, testSighting. getAnimalId());
    }

    @Test
    public void getLocation_personInstantiatesWithLocation_String() {
        Sighting testSighting = new Sighting(2,"Zone A",2);
        assertEquals("Zone A", testSighting.getLocation());
    }

    @Test
    public void getRangerId_personInstantiatesWithRangerId_String() {
        Sighting testSighting = new Sighting(2,"Zone A",2);
        assertEquals(2, testSighting.getRangerId());
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sighting testSighting = new Sighting(2,"Zone A",2);
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Sighting firstSighting = new Sighting(2,"Zone A",2);
        firstSighting.save();
        Sighting secondSighting = new Sighting(2,"Zone A",2);
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }
}