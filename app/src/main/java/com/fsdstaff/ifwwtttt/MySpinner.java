package com.fsdstaff.ifwwtttt;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adithyah on 12/7/15.
 */
public class MySpinner extends Spinner {
    private String name;
    private List<Object> items;
    private Type type;

    public MySpinner(Context context) {
        super(context);

    }

    public MySpinner setName(String name){
        this.name = name;
        return this;
    }

    public MySpinner setItems(List<Object> items){
        this.items = items;
        List<String> sItems = new ArrayList<String>();
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, items);
        this.setAdapter(adapter);
        return this;
    }

    public MySpinner setType(Type type){
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }
    public Type getType(){
        return type;
    }

    public enum Type{
        IF_APP, THEN_APP, IF_FEATURE, THEN_FEATURE, IF_VAL, THEN_VAL, IF_HOUR, IF_MIN, IF_INTERVAL
    }
}
