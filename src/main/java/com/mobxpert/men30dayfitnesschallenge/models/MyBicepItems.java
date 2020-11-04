package com.mobxpert.men30dayfitnesschallenge.models;

import java.io.Serializable;

public class MyBicepItems implements Serializable {

    String youtubelink, workoutName, exerciseName, repetiotions, description;

    public String getYoutubelink() {
        return youtubelink;
    }

    public void setYoutubelink(String youtubelink) {
        this.youtubelink = youtubelink;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getRepetiotions() {
        return repetiotions;
    }

    public void setRepetiotions(String repetiotions) {
        this.repetiotions = repetiotions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
