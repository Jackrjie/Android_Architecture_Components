package com.tim.bookstore.model;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * create by Tim on 6/7/2019
 * email: jackrjie@gmail.com
 */
public class BookStoreRepository {

    private ICategoryDAO mCategoryDAO;
    private IBookDAO mBookDAO;
    private LiveData<List<Category>> mCategories;
    private LiveData<List<Book>> mBooks;

    public BookStoreRepository(Application application){
        BooksDatabase booksDatabase = BooksDatabase.getInstance(application);
        mCategoryDAO = booksDatabase.mCategoryDAO();
        mBookDAO = booksDatabase.mBookDAO();
    }

    public LiveData<List<Category>> getCategories() {
        return mCategoryDAO.getAllCategories();
    }

    public LiveData<List<Book>> getBooks(int categoryId) {
        return mBookDAO.getBooks(categoryId);
    }

    // Insert

    public void insertCategory(final Category category){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mCategoryDAO.insert(category);
            }
        });
    }

    public void insertBook(final Book book){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mBookDAO.insert(book);
            }
        });
    }

    // Delete

    public void deleteCategory(final Category category){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mCategoryDAO.delete(category);
            }
        });
    }

    public void deleteBook(final Book book){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mBookDAO.delete(book);
            }
        });
    }

    // Update

    public void updateCategory(final Category category){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mCategoryDAO.update(category);
            }
        });
    }

    public void updateBook(final Book book){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mBookDAO.update(book);
            }
        });
    }
}
