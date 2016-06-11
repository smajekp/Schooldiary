package com.myapp.ui.profile;

/**
 * Created by Piotrek on 2016-06-11.
 */
public class ProfileFactory {
    public static Profile buildProfile(ProfileType type){
        switch(type){
            case TEACHER:
                return new Teacher();
            case EDUCATOR:
                return new Educator();
            case DIRECTOR:
                return new Director();
            default:
                return null;
        }
    }

}
