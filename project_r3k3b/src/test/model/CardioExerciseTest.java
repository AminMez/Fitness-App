package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardioExerciseTest {

    private CardioExercise c1;
    private CardioExercise c2;
    private CardioExercise c3;

    @BeforeEach
    public void init() {
        c1 = new CardioExercise("Running");
        c2 = new CardioExercise("Running");
        c2.setDuration(30);
        c3 = new CardioExercise("Treadmill");
        c3.setDuration(10);
    }


    //Test the case that cardio exercise has a name but target not set
    @Test
    public void testCardioExerciseEmpty() {
        assertEquals("Running",c1.getName());
        assertEquals(0,c1.getAmount());
    }

    @Test
    public void testCardioExerciseNotEmpty() {
        assertEquals("Running",c2.getName());
        assertEquals(30,c2.getAmount());

        assertEquals("Treadmill",c3.getName());
        assertEquals(10,c3.getAmount());
    }






}
