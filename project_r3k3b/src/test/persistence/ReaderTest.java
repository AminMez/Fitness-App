package persistence;

import model.Schedule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    void testReaderNonExistFile() {
        Reader reader = new Reader("./data/noSuchFileName.json");
        try {
            Schedule sc = reader.read();
            fail("Test should fail");
        } catch (IOException e) {
            //Test is correct

        }
    }

    @Test
    void testReaderNewSchedule() {
        Reader reader = new Reader("./data/testWriterNewSchedule.json");
        try {
            Schedule sc = reader.read();
            Schedule scTest = new Schedule(7);

            for (int i = 1; i <= 7; i++) {
                Workday expect = sc.getWorkDay(i);
                Workday returned = scTest.getWorkDay(i);

                assertEquals(0, returned.getNumWorkout());
                assertEquals(expect.getDay(), returned.getDay());
                assertEquals(expect.getNumWorkout(), returned.getNumWorkout());
            }
        } catch (IOException e) {
            fail("Should be able to read the file");
        }
    }

    @Test
    void testReaderSchedule() {
        Reader reader = new Reader("./data/testWriterSchedule.json");
        try {
            Schedule sc = reader.read();
            Schedule exampleSchedule = makeExample();
            Writer writer = new Writer("./data/testWriterSchedule.json");
            writer.openFile();
            writer.write(exampleSchedule);
            writer.close();
            
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
        } catch (IOException ee) {
            fail("Cannot read or restore the data");
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
