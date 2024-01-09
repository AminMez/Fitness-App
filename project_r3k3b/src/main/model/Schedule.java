package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;


public class Schedule {

    // A schedule represent the workout schedule of a certain person's workout plan
    // The class is used to store information about workouts on a specific day and
    // indexing the days that workout want to be added, removed or find summary

    private List<Workday> workdays; //A list of workday with their own workout schedule

    private EventLog log;

    //requires: days have to be at least 1
    //effects:  construct a schedule that has `days` may empty workdays in
    //          their schedule labeled with a day number
    public Schedule(int days) {

        workdays = new ArrayList<>();

        for (int i = 1;i <= days; i++) {
            String dayNum = String.valueOf(i);
            Workday wd = new Workday("Day" + dayNum);
            workdays.add(wd);
        }
        this.log = EventLog.getInstance();
    }

    //required: i has to be greater than one and less than or equal to the size of field `workdays`
    //effects: returns a single workday object at index i-1
    public Workday getWorkDay(int i) {
        return workdays.get(i - 1);
    }

    public List<Workday> getWorkDays() {
        return workdays;
    }

    //required: i has to be greater than one and less than or equal to the size of field `workdays`
    //effects: set (i+1) th workday in schedule to be the workday object passed to the method
    public void setWorkday(Workday wd,int i) {
        workdays.set(i, wd);
        Event e = new Event("Modify" + " " + wd.getDay());
        this.log.logEvent(e);
    }

    //EFFECTS: Convert the schedule to JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workdays", workdaysToJson());
        return json;
    }

    //EFFECTS: Convert the workdays field in schedule to an JSONArray
    private JSONArray workdaysToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workday w : workdays) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}
