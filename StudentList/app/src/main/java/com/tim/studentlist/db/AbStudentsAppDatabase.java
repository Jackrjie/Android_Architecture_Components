package com.tim.studentlist.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tim.studentlist.db.entity.Student;

/**
 * create by Tim on 6/3/2019
 * email: jackrjie@gmail.com
 */
@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class AbStudentsAppDatabase extends RoomDatabase {
    public abstract IStudentDAO getStudentDAO();
}
