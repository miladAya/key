package com.example.keymystery.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(foreignKeys = {@ForeignKey(entity = Levels.class,parentColumns = {"level_no"} ,childColumns = {"level_no"}
,onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class Question {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idQ")
    private long idQ;
     private long id;

    @ColumnInfo(name = "level_no")
    @NonNull
    private long level_no;
    @ColumnInfo(name = "title")
    @NonNull
    private String title;
    @ColumnInfo(name = "answer_1")

    private String answer_1;
    @ColumnInfo(name = "answer_2")

    private String answer_2;
    @ColumnInfo(name = "answer_3")

    private String answer_3;
    @ColumnInfo(name = "answer_4")

    private String answer_4;
    @ColumnInfo(name = "true_answer")

    private String true_answer;
    @ColumnInfo(name = "points")

    private int points;
    @ColumnInfo(name = "duration")
    private long duration;
    @ColumnInfo(name = "hint")
    String hint;

    private  final int pattern_id;
    private  final String  pattern_name;



    public long getIdQ() {
        return idQ;
    }

    public void setIdQ(long idQ) {
        this.idQ = idQ;
    }

    public long getLevel_no() {
        return level_no;
    }

    public void setLevel_no(long level_no) {
        this.level_no = level_no;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public Question(long id, long level_no, @NonNull String title, String answer_1, String answer_2, String answer_3, String answer_4, String true_answer, int points, long duration, String hint, int pattern_id, String pattern_name) {
        this.id = id;
        this.level_no = level_no;
        this.title = title;
        this.answer_1 = answer_1;
        this.answer_2 = answer_2;
        this.answer_3 = answer_3;
        this.answer_4 = answer_4;
        this.true_answer = true_answer;
        this.points = points;
        this.duration = duration;
        this.hint = hint;
        this.pattern_id = pattern_id;
        this.pattern_name = pattern_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(@NonNull String title) {
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getPattern_id() {
        return pattern_id;
    }

    public String getPattern_name() {
        return pattern_name;
    }
}
