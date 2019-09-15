package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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
}