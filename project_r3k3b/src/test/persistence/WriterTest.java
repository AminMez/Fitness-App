package persistence;

import model.CardioExercise;
import model.Schedule;
import model.StrengthExercise;
import model.Workday;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {

    @Test
    void testWriterInvalid() {
        try {
            Schedule sc = new Schedule(7);
            Writer writer = new Writer("./data/my\0illegal:fileName.json");
            writer.openFile();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNewSchedule() {
        try {
            Schedule sc1 = new Schedule(7);
            Writer writer = new Writer("./data/testWriterNewSchedule.json");
            writer.openFile();
            writer.write(sc1);
            writer.close();

            Reader reader = new Reader("./data/testWriterNewSchedule.json");
            Schedule sc2 = reader.read();

            for (int i = 1; i <= 7; i++) {
                Workday expect = sc1.getWorkDay(i);
                Workday returned = sc2.getWorkDay(i);

                assertEquals(0, returned.getNumWorkout());
                assertEquals(expect.getDay(), returned.getDay());
                assertEquals(expect.getNumWorkout(), returned.getNumWorkout());
            }

        } catch (IOException e) {
            fail("No exception should be thrown");
        }
    }

    @Test
    void testWriterModifiedSchedule() {
        try {
            Schedule exampleSchedule = makeExample();
            Writer writer = new Writer("./data/testWriterSchedule.json");
            writer.openFile();
            writer.write(exampleSchedule);
            writer.close();

            Reader reader = new Reader("./data/testWriterSchedule.json");
            Schedule returnedSchedule = reader.read();

            assertEquals("Day1",returnedSchedule.getWorkDay(1).getDay());
            assertEquals("Day3",returnedSchedule.getWorkDay(3).getDay());


            assertEquals(exampleSchedule.getWorkDay(1).getCardioExercises().size(),
                    returnedSchedule.getWorkDay(1).getCardioExercises().size());

            assertEquals(exampleSchedule.getWorkDay(3).getStrengthExercise().size(),
                    returnedSchedule.getWorkDay(3).getStrengthExercise().size());

            assertEquals(1,returnedSchedule.getWorkDay(1).getCardioExercises().size());
            assertEquals(2,returnedSchedule.getWorkDay(3).getStrengthExercise().size());

            assertEquals("Pull up",
                    returnedSchedule.getWorkDay(1).getStrengthExercise().get(0).getName());
            assertEquals(160,
                    returnedSchedule.getWorkDay(1).getStrengthExercise().get(0).getWeight());


            assertEquals("Squat",
                    returnedSchedule.getWorkDay(3).getStrengthExercise().get(1).getName());
            assertEquals(225,
                    returnedSchedule.getWorkDay(3).getStrengthExercise().get(1).getWeight());

        } catch (IOException e) {
            fail("No exception should be thrown");
        }
    }


    private Schedule makeExample() {
        Schedule sc = new Schedule(7);
        Workday wd1 = new Workday("Day1");
        Workday wd3 = new Workday("Day3");

        CardioExercise ce1 = new CardioExercise("Running");
        CardioExercise ce2 = new CardioExercise("Hiking");
        ce1.setDuration(30);
        ce2.setDuration(10);

        StrengthExercise se1 = new StrengthExercise("Pull up", "back");
        StrengthExercise se2 = new StrengthExercise("Bench press", "chest");
        StrengthExercise se3 = new StrengthExercise("Squat", "leg");
        se1.setReps(10);
        se1.setSets(4);
        se1.setWeight(160);

        se2.setReps(6);
        se2.setSets(3);
        se2.setReps(160);

        se3.setReps(5);
        se3.setSets(4);
        se3.setWeight(225);

        wd1.addCardioWorkout(ce1);
        wd1.addStrengthWorkout(se1);

        wd3.addStrengthWorkout(se2);
        wd3.addStrengthWorkout(se3);
        wd3.addCardioWorkout(ce2);

        sc.setWorkday(wd1, 0);
        sc.setWorkday(wd3, 2);

        return sc;
    }

}

