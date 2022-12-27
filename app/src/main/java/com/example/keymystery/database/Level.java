package com.example.keymystery.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

@Entity
//@TypeConverters(Converter.class)

public class Level {

    @PrimaryKey(autoGenerate = false)
    @NonNull
     int level_no;
     int unlock_points;
    @TypeConverters({Converter.class})

    ArrayList<Question> questions;

    public Level(int level_no, int unlock_points, ArrayList<Question> questions) {
        this.level_no = level_no;
        this.unlock_points = unlock_points;
        this.questions = questions;
    }

    public int getLevel_no() {
        return level_no;
    }

    public void setLevel_no(int level_no) {
        this.level_no = level_no;
    }

    public int getUnlock_points() {
        return unlock_points;
    }

    public void setUnlock_points(int unlock_points) {
        this.unlock_points = unlock_points;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public static class Pattern{
        private int pattern_id;
        private String pattern_name;

        public Pattern(int pattern_id, String pattern_name) {
            this.pattern_id = pattern_id;
            this.pattern_name = pattern_name;
        }

        public int getPattern_id() {

            return pattern_id;
        }

        public void setPattern_id(int pattern_id) {
            this.pattern_id = pattern_id;
        }

        public String getPattern_name() {
            return pattern_name;
        }

        public void setPattern_name(String pattern_name) {
            this.pattern_name = pattern_name;
        }
    }

    public static class Question{
        private int id;
        private String title;
        private String answer_1;
        private String answer_2;
        private String answer_3;
        private String answer_4;
        private String true_answer;
        private int points;
        private int duration;
        private Pattern pattern;
        private String hint;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAnswer_1() {
            return answer_1;
        }

        public void setAnswer_1(String answer_1) {
            this.answer_1 = answer_1;
        }

        public String getAnswer_2() {
            return answer_2;
        }

        public void setAnswer_2(String answer_2) {
            this.answer_2 = answer_2;
        }

        public String getAnswer_3() {
            return answer_3;
        }

        public void setAnswer_3(String answer_3) {
            this.answer_3 = answer_3;
        }

        public String getAnswer_4() {
            return answer_4;
        }

        public void setAnswer_4(String answer_4) {
            this.answer_4 = answer_4;
        }

        public String getTrue_answer() {
            return true_answer;
        }

        public void setTrue_answer(String true_answer) {
            this.true_answer = true_answer;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public Pattern getPattern() {
            return pattern;
        }

        public void setPattern(Pattern pattern) {
            this.pattern = pattern;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public Question(int id, String title, String answer_1, String answer_2, String answer_3, String answer_4,
                        String true_answer, int points, int duration, Pattern pattern, String hint) {
            this.id = id;
            this.title = title;
            this.answer_1 = answer_1;
            this.answer_2 = answer_2;
            this.answer_3 = answer_3;
            this.answer_4 = answer_4;
            this.true_answer = true_answer;
            this.points = points;
            this.duration = duration;
            this.pattern = pattern;
            this.hint = hint;

        }
    }
}
