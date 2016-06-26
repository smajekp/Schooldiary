package com.myapp.ui.profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Piotrek on 2016-06-26.
 */
public class TeacherCaretaker {

    private Map<String, TeacherMemento> savedStates = new HashMap<String, TeacherMemento>();

    public void addMemento(String key, TeacherMemento value) {
        this.savedStates.put(key, value);
    }

    public TeacherMemento getMemento(String key) {
        return this.savedStates.get(key);
    }
}
