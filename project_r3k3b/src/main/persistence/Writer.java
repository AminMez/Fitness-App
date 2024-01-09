package persistence;

import model.Event;
import model.EventLog;
import model.Schedule;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


// The class represents a writer that convert the data entered into the terminal into a json file

public class Writer {
    private static final int TAB = 4;
    private PrintWriter writer;         //the writer object that writes the file
    private String destination;         //the path where the writted file is storing at

    private EventLog log;


    //MODIFIES: this
    //EFFECTS: Construct a writer that write files to the setted destination
    public Writer(String des) {

        this.destination = des;
        this.log = EventLog.getInstance();
    }

    //MODIFIES: this
    //EFFECTS: Open up a new file for the writer, if the file is not found, throw the exception.
    public void openFile() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: convert the schedule object created in the terminal into json data
    public void write(Schedule sc) {
        JSONObject json = sc.toJson();
        saveToFile(json.toString(TAB));
        Event e = new Event("Save schedule");
        this.log.logEvent(e);
    }

    //MODIFIES: this
    //EFFECTS: close the writer
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: write string to file
    private void saveToFile(String json) {
        writer.print(json);
    }



}
