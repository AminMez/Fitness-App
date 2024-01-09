package model;

import org.json.JSONObject;

public class StrengthExercise implements Exercise {


    //Record a single strength exercise in the gym. Representing the information
    // that is important to characterise a strength exercise

    private String name; //The name of the workout

    private String part; // The part of muscle group that is engaged in this workout

    private int reps; // The target reps of the exercise

    private int sets; // The target sets of the exercise

    private int weight; // The weight of the exercise in pounds

    //Requires: Both name and part need string length > 0
    //effects: create a workout with its name, training part and starts with 0 on sets reps and weight;
    public StrengthExercise(String name, String part) {
        this.name = name;
        this.part = part;
        this.reps = 0;
        this.sets = 0;
        this.weight = 0;
    }

    @Override
    public int getAmount() {
        int volume = reps * sets * weight;
        return volume;
    }

    @Override
    public String getName() {
        return name;
    }

    //Requires: set have to be greater than 0
    //Modifier: this
    //Effects: set the exercise sets to `set` amount
    public void setSets(int set) {
        this.sets = set;
    }

    //Requires: rep have to be greater than 0
    //Modifier: this
    //Effects: set the exercise reps to `rep` amount
    public void setReps(int rep) {
        this.reps = rep;
    }

    //Requires: weight have to be greater than 0
    //Modifier: this
    //Effects: set the exercise weight to `weight` amount in pounds
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPart() {
        return part;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    //EFFECTS: Convert the Strength Exercise object to json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("part", part);
        json.put("reps",reps);
        json.put("sets",sets);
        json.put("weight",weight);
        return json;
    }

}
