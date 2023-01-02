package com.example.keymystery.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Levels {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "level_no")
    long level_no;
    @ColumnInfo(name = "unlock_points")
   int unlock_points;

    public long getLevel_no() {
        return level_no;
    }

    public void setLevel_no(long level_no) {
        this.level_no = level_no;
    }

    public int getUnlock_points() {
        return unlock_points;
    }

    public void setUnlock_points(int unlock_points) {
        this.unlock_points = unlock_points;
    }

    public Levels(long level_no, int unlock_points) {
        this.level_no = level_no;
        this.unlock_points = unlock_points;
    }
}
