package com.fsdstaff.ifwwtttt;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adithyah on 12/7/15.
 */
public class CreateRuleSpinner extends Spinner {
    private String name;
    private List<Object> items;
    private Type type;

    public CreateRuleSpinner(Context context) {
        super(context);
        items = new ArrayList<>();
    }

    public CreateRuleSpinner setName(String name){
        this.name = name;
        this.items.add(0, " -- " + name + " -- ");
        return this;
    }

    public CreateRuleSpinner setItems(List<Object> items){
        this.items.addAll(items);
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, this.items);
        this.setAdapter(adapter);
        return this;
    }

    public CreateRuleSpinner setType(Type type){
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
