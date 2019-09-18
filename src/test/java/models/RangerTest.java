package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangerTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void instantiatesCorrectly_true(){
        Ranger ranger = new Ranger(1,"Daisy", 3456,724778856);
        assertEquals(true, ranger instanceof Ranger);
    }


}