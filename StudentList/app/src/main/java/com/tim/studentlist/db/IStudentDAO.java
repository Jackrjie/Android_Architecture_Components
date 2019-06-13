package com.tim.studentlist.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tim.studentlist.db.entity.Student;

import java.util.List;

/**
 * create by Tim on 6/3/2019
 * email: jackrjie@gmail.com
 */
@Dao
public interface IStudentDAO {
    @Insert
    void addStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("select * from table_student")
    List<Student> getAllStudents();
}
