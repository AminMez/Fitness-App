package persistence;

import model.*;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// The class represent a reader that read file from the stored folder and convert the data
// from JSON format into schedule object again

public class Reader {

    private String source;       //The path of the file to read from

    private EventLog log;

    //EFFECTS: Construct a reader object reading from the source `source`
    public Reader(String source) {

        this.source = source;
        this.log = EventLog.getInstance();
    }



    //EFFECTS: Read the data from source file and construct the schedule from JSON file.
    public Schedule read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        Event e = new Event("Loading schedule");
        this.log.logEvent(e);
        return parseSchedule(jsonObject);
    }

    //EFFECTS: Read the json data from the source. Throw IOException when the file is not found.
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parse the schedule that was stored in JSON object and returns the schedule,
    //         add the workdays into the created schedule and return the schedule.
    private Schedule parseSchedule(JSONObject j) {
        Schedule sc = new Schedule(7);
        JSONArray jsonArray = j.getJSONArray("workdays");
        int i = 0;

        for (Object o:jsonArray) {
            JSONObject jobject = (JSONObject) o;
            Workday wd = parseWorkday(jobject);
            sc.setWorkday(wd,i);
            i++;
        }
        return sc;
    }

    //EFFECTS: parse the workday that was stored as a JSON object and return the workday.
    private Workday parseWorkday(JSONObject jobject) {
        String day = jobject.getString("day");
        Workday wd = new Workday(day);
        JSONArray jsonArray = jobject.getJSONArray("exercises");

        for (Object j:jsonArray) {
            JSONObject nextExercise = (JSONObject) j;
            addExercise(wd,nextExercise);
        }
        return wd;
    }

    //MODIFIES: Workday
    //EFFECTS:  parses exercises (cardio and strength) from JSON object and adds it to the
    //          workday object that is passed to the function.
    private void addExercise(Workday wd,JSONObject jobject) {
        if (jobject.has("duration")) {
            String name = jobject.getString("name");
            int duration = jobject.getInt("duration");
            CardioExercise ce = new CardioExercise(name);
            ce.setDuration(duration);
            wd.addCardioWorkout(ce);
        } else {
            String name = jobject.getString("name");
            String part = jobject.getString("part");
            int reps = jobject.getInt("reps");
            int sets = jobject.getInt("sets");
            int weights = jobject.getInt("weight");
            StrengthExercise se = new StrengthExercise(name,part);
            se.setReps(reps);
            se.setSets(sets);
            se.setWeight(weights);
            wd.addStrengthWorkout(se);
        }
    }

}

