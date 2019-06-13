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
public interface IBookDAO {

    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("SELECT * FROM book_table")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT * FROM book_table WHERE category_id==:categoryId")
    LiveData<List<Book>> getBooks(int categoryId);

}
