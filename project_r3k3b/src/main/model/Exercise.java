package model;

import org.json.JSONObject;

public interface Exercise {

    int getAmount();

    String getName();

    JSONObject toJson();

}
