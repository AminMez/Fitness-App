package model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

//A workday represent the list of workouts in a certain day.
//It stores the list of cardio and weight training and able to modify
//workout schedule by adding and removing workouts. Also provide some
//summaries such as the number of workout, duration of workout or
//workout volume by parts.

public class Workday {

    private List<Exercise> exercises;  // A list of exercises in the current workday

    private ArrayList<CardioExercise> cardio; // A list of cardio exercises in the current workday

    private ArrayList<StrengthExercise> strength; // A list of strength exercises in the current workday

    private String day; // The name of the day. Usually the labeled with day number

    private EventLog log;

    //Requires: `day` is a string with length greater than 1
    //Effects: Create a workday with `day` day and an empty arraylist of storing all exercises or
    //         different type of workout
    public Workday(String day) {
        this.day = day;
        this.exercises = new ArrayList<>();
        this.cardio = new ArrayList<>();
        this.strength = new ArrayList<>();
        this.log = EventLog.getInstance();
    }

    //Modifier: this
    //Effects: Add cardioworkout to the list of workout and `cardio`
    public void addCardioWorkout(CardioExercise ce) {
        exercises.add(ce);
        cardio.add(ce);
        Event e = new Event("Add Cardio exercise" + " " + ce.getName());
        log.logEvent(e);
    }

    //Modifier: this
    //Effects: Add strengthworkout to the list of workout and `strength`
    public void addStrengthWorkout(StrengthExercise se) {
        exercises.add(se);
        strength.add(se);
        Event e = new Event("Add Strength exercise" + " " + se.getName());
        log.logEvent(e);
    }

    //Effects: find the training amount for certain part of muscle group
    //         return 0 if the part does not exist or there are no volume
    //         for the exercise.
    public int getPartVolume(String part) {
        int sum = 0;
        for (StrengthExercise se: strength) {
            if (se.getPart().equals(part)) {
                sum += se.getAmount();
            }
        }
        return sum;
    }

    //effects: get the total duration of cardio workout on a certain day
    public int getTotalDuration() {
        int sum = 0;
        for (CardioExercise ce: cardio) {
            sum += ce.getAmount();
        }
        return sum;
    }

    //Modifies: this
    //Effects: remove ALL the workout with the name `name` in the list of workout, cardio
    //         and strength training. Remove all duplicate at once. Return true of there
    //         are things removed and return false if there are not things removed

    public boolean removeWorkout(String name) {
        if (exercises.removeIf(exercise -> exercise.getName().equals(name))) {
            Event e = new Event("Removed exercise" + " " + name);
            log.logEvent(e);
            if (cardio.removeIf(cardioExercise -> cardioExercise.getName().equals(name))) {
                return true;
            } else if ((strength.removeIf(strengthExercise -> strengthExercise.getName().equals(name)))) {
                return true;
            }
        }
        Event e = new Event("No exercise is removed");
        log.logEvent(e);
        return false;
    }


    //EFFECTS: return the name of exercises in string
    public List<String> getExercisesString() {
        List<String> names = new ArrayList<>();
        for (Exercise e:exercises) {
            names.add(e.getName());
        }
        return names;
    }

    public ArrayList<StrengthExercise> getStrengthExercise() {
        return strength;
    }

    public ArrayList<CardioExercise> getCardioExercises() {
        return cardio;
    }

    public int getNumWorkout() {
        return exercises.size();
    }

    public String getDay() {
        return day;
    }


    //EFFECTS: Convert Workday to JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("exercises", exercisesToJson());
        return json;
    }

    //EFFECTS: Convert the field list of exercises to JSONArray
    private JSONArray exercisesToJson() {
        JSONArray jarray = new JSONArray();

        for (Exercise e : exercises) {
            jarray.put(e.toJson());
        }
        return jarray;
    }
}
