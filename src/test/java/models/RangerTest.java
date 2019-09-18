package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangersTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void instantiatesCorrectly_true(){
        Animal animal =  setUpNewAnimal();
        assertEquals(true, animal instanceof Animal);
    }

}