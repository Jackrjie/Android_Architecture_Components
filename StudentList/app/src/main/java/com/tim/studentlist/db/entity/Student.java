package com.tim.studentlist.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * create by Tim on 6/3/2019
 * email: jackrjie@gmail.com
 */

@Entity(tableName = "table_student")
public class Student {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String email;
    private String country;
    private String registeredDate;

    @Ignore
    public Student(){}

    public Student(long id, String name, String email, String country, String registeredDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.registeredDate = registeredDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
