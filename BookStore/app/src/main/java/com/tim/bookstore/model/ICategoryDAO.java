package com.tim.bookstore.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * create by Tim on 6/7/2019
 * email: jackrjie@gmail.com
 */

@Dao
public interface ICategoryDAO {

    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("SELECT * FROM category_table")
    LiveData<List<Category>> getAllCategories();

}
