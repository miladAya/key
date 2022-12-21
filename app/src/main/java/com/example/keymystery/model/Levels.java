package com.example.keymystery.model;

public class Levels {
    int levelNumber;
    int requiredPoints ;
    int score;

    public int getNumberLevels() {
        return levelNumber;
    }

    public void setNumberLevels(int numberLevels) {
        this.levelNumber = numberLevels;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Levels(int levelNumber, int requiredPoints, int score) {
        this.levelNumber = levelNumber;
        this.requiredPoints = requiredPoints;
        this.score = score;
    }
}
