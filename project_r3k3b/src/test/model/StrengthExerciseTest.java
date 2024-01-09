package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrengthExerciseTest {

    private StrengthExercise s1;
    private StrengthExercise s2;
    private StrengthExercise s3;


    @BeforeEach
    public void init() {
        s1 = new StrengthExercise("Bench press","Chest");
        s1.setSets(4);
        s1.setReps(10);
        s1.setWeight(150);
        s2 = new StrengthExercise("Barbell squat","Leg");
        s3 = new StrengthExercise("Low row","Back");
        s3.setSets(3);
        s3.setReps(12);
        s3.setWeight(150);
    }

    @Test
    public void testStrengthExerciseEmpty() {
        assertEquals("Barbell squat",s2.getName());
        assertEquals("Chest",s1.getPart());
        assertEquals(0,s2.getWeight());
        assertEquals(0,s2.getSets());
        assertEquals(0,s2.getReps());
        assertEquals(0,s2.getAmount());
    }

    @Test
    public void testStrengthExerciseNotEmpty() {
        assertEquals("Bench press",s1.getName());
        assertEquals("Chest",s1.getPart());
        assertEquals(150,s1.getWeight());
        assertEquals(4,s1.getSets());
        assertEquals(6000,s1.getAmount());
        assertEquals(10,s1.getReps());

        assertEquals("Low row",s3.getName());
        assertEquals("Back",s3.getPart());
        assertEquals(150,s3.getWeight());
        assertEquals(3,s3.getSets());
        assertEquals(5400,s3.getAmount());
        assertEquals(12,s3.getReps());
    }
}
