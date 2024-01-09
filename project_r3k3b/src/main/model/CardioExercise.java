package model;

import org.json.JSONObject;

public class CardioExercise implements Exercise {

    // A cardio exercise is a type of exercise with a name and duration
    // It is to give customer a different type of workout to add onto
    // their fitness app.
    private String name;     // Name of the Cardio Workout

    private int duration;    // The time goal of the cardio workout

    //Required: Name must be length > 0
    //Effects: Create a cardio workout with a name and duration starting at 0 minutes.
    public CardioExercise(String name) {
        this.name = name;
        this.duration = 0;
    }

    //Required: duration must be > 0
    //Modifies: this
    //Effects: Set the cardio workout duration to `duration` minutes
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int getAmount() {
        return duration;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    //EFFECTS: Convert the CardioExercise object to JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("duration", duration);
        return json;
    }
}