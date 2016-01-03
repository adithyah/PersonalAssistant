package com.fsdstaff.ifwwtttt;

import java.util.List;

/**
 * Created by adithyah on 12/18/15.
 */
public class Feature {
    String name;
    List<String> actions;
    List<String> values;

    public String getName() {
        return name;
    }

    public List<String> getValues() {
        return values;
    }

    public String toString() {
        return name;
    }

    public List<String> getActions() {
        return actions;
    }
}
