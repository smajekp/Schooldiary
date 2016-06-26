package com.myapp.ui.profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Piotrek on 2016-06-26.
 */
public class StudentCaretaker {

    private Map<String, StudentMemento> savedStates = new HashMap<String, StudentMemento>();

    public void addMemento(String key, StudentMemento value) {
        this.savedStates.put(key, value);
    }

    public StudentMemento getMemento(String key) {
        return this.savedStates.get(key);
    }
}
