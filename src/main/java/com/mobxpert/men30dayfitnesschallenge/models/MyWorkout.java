package com.mobxpert.men30dayfitnesschallenge.models;

import java.io.Serializable;

public class MyWorkout implements Serializable {

    String  youtubeId;
    int workout,name, repetition;


    boolean isExerciseDone;

    public MyWorkout(int name, int workout, String youtubeId, int repetition) {
        this.name = name;
        this.workout = workout;
        this.youtubeId = youtubeId;
        this.repetition = repetition;
    }

    public boolean isExerciseDone() {
        return isExerciseDone;
    }

    public void setExerciseDone(boolean exerciseDone) {
        isExerciseDone = exerciseDone;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    //ImageView imageView;

    public int getWorkout() {
        return workout;
    }

    public void setWorkout(int workout) {
        this.workout = workout;
    }


    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

}
