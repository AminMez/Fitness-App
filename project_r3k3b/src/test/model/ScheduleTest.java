package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class ScheduleTest {

    private Schedule schedule;

    public Workday w_test;

    public CardioExercise ce1;
    public CardioExercise ce2;

    public StrengthExercise se1;
    public StrengthExercise se2;
    public StrengthExercise se3;
    public StrengthExercise se4;
    public StrengthExercise se5;

    @Test
    public void testConstructor() {
        schedule = new Schedule(7);
        List<Workday> wds = schedule.getWorkDays();
        assertEquals("Day1",wds.get(0).getDay());
    }

    @Test
    public void testSetWorkday() {
        schedule = new Schedule(7);
        schedule.setWorkday(w_test,1);

        List<Workday> wds = schedule.getWorkDays();
        assertEquals("9/20",wds.get(1).getDay());
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
