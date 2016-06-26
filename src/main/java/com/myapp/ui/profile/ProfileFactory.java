package com.myapp.ui.profile;

/**
 * Created by Piotrek on 2016-06-11.
 */
public class ProfileFactory {
    public static Profile buildProfile(ProfileType type){
        switch(type){
            case TEACHER:
                return Teacher.getInstance();
            case EDUCATOR:
                return Educator.getInstance();
            case DIRECTOR:
                return Director.getInstance();
            default:
                return null;
        }
    }

}
