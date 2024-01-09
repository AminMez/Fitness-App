package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkDayTest {

    public Workday w_test;

    public CardioExercise ce1;
    public CardioExercise ce2;

    public StrengthExercise se1;
    public StrengthExercise se2;
    public StrengthExercise se3;
    public StrengthExercise se4;
    public StrengthExercise se5;



    @Test
    public void testWorkDayEmpty() {
        assertEquals("9/20",w_test.getDay());
        assertEquals(0,w_test.getNumWorkout());
        assertTrue(w_test.getExercisesString().isEmpty());
    }

    @Test
    public void testAllCardioWorkout() {
        w_test.addCardioWorkout(ce1);
        w_test.addCardioWorkout(ce2);
        assertEquals(2,w_test.getNumWorkout());
        assertEquals(70,w_test.getTotalDuration());
    }

    @Test
    public void testAllStrengthWorkout() {
        w_test.addStrengthWorkout(se1);
        w_test.addStrengthWorkout(se2);
        w_test.addStrengthWorkout(se3);

        assertEquals(3,w_test.getNumWorkout());
        assertEquals(5920,w_test.getPartVolume("Shoulder"));
        assertEquals(4800,w_test.getPartVolume("Back"));
    }

    @Test
    public void testGetPartVolumeMix() {
        w_test.addStrengthWorkout(se1);
        w_test.addStrengthWorkout(se2);
        w_test.addStrengthWorkout(se3);
        w_test.addCardioWorkout(ce1);

        assertEquals(4,w_test.getNumWorkout());
        assertEquals(5920,w_test.getPartVolume("Shoulder"));
    }

    @Test
    public void testGetTotalDuration() {
        w_test.addCardioWorkout(ce1);
        w_test.addStrengthWorkout(se2);
        w_test.addStrengthWorkout(se3);
        w_test.addCardioWorkout(ce2);

        assertEquals(4,w_test.getNumWorkout());
        assertEquals(70,w_test.getTotalDuration());
    }

    @Test
    public void testRemoveWorkoutFalse() {
        w_test.addCardioWorkout(ce1);
        w_test.addStrengthWorkout(se2);
        w_test.addStrengthWorkout(se3);

        assertEquals(3,w_test.getNumWorkout());

        assertFalse(w_test.removeWorkout("Dips"));

        assertEquals(3,w_test.getNumWorkout());
    }

    @Test
    public void testRemoveWorkoutTrueCardio() {
        w_test.addCardioWorkout(ce1);
        w_test.addStrengthWorkout(se2);
        w_test.addStrengthWorkout(se3);

        assertEquals(3,w_test.getNumWorkout());

        assertTrue(w_test.removeWorkout("Running"));

        assertEquals(2,w_test.getNumWorkout());
        assertEquals("Overhead press",w_test.getExercisesString().get(0));

        assertTrue(w_test.removeWorkout("Pull up"));
        assertEquals(1,w_test.getNumWorkout());
        assertEquals("Overhead press",w_test.getExercisesString().get(0));
    }

    @Test
    public void testRemoveWorkoutTrueStrength() {
        w_test.addCardioWorkout(ce1);
        w_test.addStrengthWorkout(se2);
        w_test.addStrengthWorkout(se3);

        assertTrue(w_test.removeWorkout("Overhead press"));

        assertEquals(2,w_test.getNumWorkout());
    }



    @Test
    public void testRemoveWorkoutDuplicate() {
        w_test.addCardioWorkout(ce1);
        w_test.addStrengthWorkout(se2);
        w_test.addStrengthWorkout(se3);
        w_test.addCardioWorkout(ce1);

        assertTrue(w_test.removeWorkout("Running"));
        assertEquals(2,w_test.getNumWorkout());
        assertEquals("Pull up",w_test.getExercisesString().get(1));
    }

    @BeforeEach
    public void inti() {
        w_test = new Workday("9/20");

        ce1 = new CardioExercise("Running");
        ce1.setDuration(25);

        ce2 = new CardioExercise("Biking");
        ce2.setDuration(45);

        se1 = new StrengthExercise("Lateral Raises","Shoulder");
        se1.setSets(4);
        se1.setReps(12);
        se1.setWeight(30);

        se2 = new StrengthExercise("Overhead press","Shoulder");
        se2.setSets(4);
        se2.setReps(7);
        se2.setWeight(160);

        se3 = new StrengthExercise("Pull up","Back");
        se3.setSets(3);
        se3.setReps(10);
        se3.setWeight(160);

        se4 = new StrengthExercise("Bicep curl","Arms");
        se4.setSets(3);
        se4.setReps(12);
        se4.setWeight(25);

        se5 = new StrengthExercise("Deadlift","Legs");
        se5.setSets(4);
        se5.setReps(3);
        se5.setWeight(240);
    }
}
