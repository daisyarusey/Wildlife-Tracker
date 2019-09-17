package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;
import java.sql.Timestamp;

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
        Sighting testSighting = new Sighting(2, "Zone A", 2);
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void getAnimalId_sightingInstantiatesWithAnimalId_2() {
        Sighting testSighting = new Sighting(2, "Zone A", 2);
        assertEquals(2, testSighting.getAnimal_id());
    }

    @Test
    public void getLocation_personInstantiatesWithLocation_String() {
        Sighting testSighting = new Sighting(2, "Zone A", 2);
        assertEquals("Zone A", testSighting.getLocation());
    }

    @Test
    public void getRangerId_personInstantiatesWithRangerId_String() {
        Sighting testSighting = new Sighting(2, "Zone A", 2);
        assertEquals(2, testSighting.getRanger_id());
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sighting testSighting = new Sighting(2, "Zone A", 2);
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Sighting firstSighting = new Sighting(2, "Zone A", 2);
        firstSighting.save();
        Sighting secondSighting = new Sighting(4, "Zone B", 2);
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }

    @Test
    public void save_assignsIdToObject() {
        Sighting testSighting = new Sighting(2, "Zone A", 2);
        testSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Sighting firstSighting = new Sighting(2, "Zone A", 2);
        firstSighting.save();
        Sighting secondSighting = new Sighting(4, "Zone B", 2);
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        Sighting testSighting = new Sighting(2, "Zone A", 2);
        testSighting.save();
        Timestamp savedSightingRecordingTime = Sighting.find(testSighting.getId()).getTimeRecorded();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(), savedSightingRecordingTime.getDay());
    }
}