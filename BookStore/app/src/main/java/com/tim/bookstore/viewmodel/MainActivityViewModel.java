package com.tim.bookstore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tim.bookstore.model.Book;
import com.tim.bookstore.model.BookStoreRepository;
import com.tim.bookstore.model.Category;

import java.util.List;

/**
 * create by Tim on 6/7/2019
 * email: jackrjie@gmail.com
 * When we need to use Application, we should extends AndroidViewModel.
 * Otherwise we can just extend the ViewModel instead of AndroidViewModel.
 */
public class MainActivityViewModel extends AndroidViewModel {

    private BookStoreRepository mBookStoreRepository;
    private LiveData<List<Category>> mAllCategories;
    private LiveData<List<Book>> mBooksOfASelectedCategory;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mBookStoreRepository = new BookStoreRepository(application);
    }

    public LiveData<List<Category>> getAllCategories(){
        mAllCategories = mBookStoreRepository.getCategories();
        return mAllCategories;
    }

    public LiveData<List<Book>> getBooksOfASelectedCategory(int categoryId) {
        mBooksOfASelectedCategory = mBookStoreRepository.getBooks(categoryId);
        return mBooksOfASelectedCategory;
    }

    public void addNewBook(Book book){
        mBookStoreRepository.insertBook(book);
    }

    public void updateBook(Book book){
        mBookStoreRepository.updateBook(book);
    }

    public void deleteBook(Book book){
        mBookStoreRepository.deleteBook(book);
    }
}
