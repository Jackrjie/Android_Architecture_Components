package com.tim.bookstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.tim.bookstore.adapter.BooksAdapter;
import com.tim.bookstore.databinding.ActivityMainBinding;
import com.tim.bookstore.model.Book;
import com.tim.bookstore.model.Category;
import com.tim.bookstore.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mMainActivityViewModel;
    private ArrayList<Category> mCategoriesList;
    private ArrayList<Book> mBooksList;
    private ActivityMainBinding mActivityMainBinding;
    private MainActivityClickHandlers mClickHandlers;
    private Category mSelectedCategory;
    private RecyclerView mRVBooks;
    private BooksAdapter mBooksAdapter;
    private int selectedBookId;

    private static final String TAG = "MainActivityTag";
    public static final int ADD_BOOK_REQUEST_CODE = 1;
    public static final int EDIT_BOOK_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mClickHandlers = new MainActivityClickHandlers();
        mActivityMainBinding.setClickHandler(mClickHandlers);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                mCategoriesList = (ArrayList<Category>)categories;
                for (Category c : categories){
                    Log.i(TAG, c.getCategoryName());
                }
                showOnSpinner();
            }
        });
    }

    private void showOnSpinner() {
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this,R.layout.spinner_item, mCategoriesList);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        mActivityMainBinding.setSpinnerAdapter(adapter);
    }

    private void loadBooksArrayList(int categoryId){
        mMainActivityViewModel.getBooksOfASelectedCategory(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                mBooksList = (ArrayList<Book>) books;
                loadRecyclerView();
            }
        });
    }

    private void loadRecyclerView(){
        mRVBooks = mActivityMainBinding.rvBooks;
        mRVBooks.setLayoutManager(new LinearLayoutManager(this));
        mRVBooks.setHasFixedSize(true);

        mBooksAdapter = new BooksAdapter();
        mRVBooks.setAdapter(mBooksAdapter);

        mBooksAdapter.setBookList(mBooksList);
        mBooksAdapter.setListener(new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                selectedBookId = book.getBookId();
                Intent intent = new Intent(getApplicationContext(),AddAndEditActivity.class);
                intent.putExtra(AddAndEditActivity.BOOK_ID,selectedBookId);
                intent.putExtra(AddAndEditActivity.BOOK_NAME,book.getBookName());
                intent.putExtra(AddAndEditActivity.UNIT_PRICE,book.getUnitPrice());
                startActivityForResult(intent,EDIT_BOOK_REQUEST_CODE);
            }
        });
    }

    private void show(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public class MainActivityClickHandlers{
        public void onFABClicked(View view){
            Intent intent = new Intent(getApplicationContext(),AddAndEditActivity.class);
            startActivityForResult(intent,ADD_BOOK_REQUEST_CODE);
        }

        public void onSelectItem(AdapterView<?> parent, View view, int position, long id){
            mSelectedCategory = (Category)parent.getItemAtPosition(position);
            loadBooksArrayList(mSelectedCategory.getId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int selectedCategoryId = mSelectedCategory.getId();
        if (requestCode == ADD_BOOK_REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null) {
                Book book = new Book();
                book.setCategoryId(selectedCategoryId);
                book.setBookName(data.getStringExtra(AddAndEditActivity.BOOK_NAME));
                book.setUnitPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));
                mMainActivityViewModel.addNewBook(book);
            } else {
                show("Something went wrong...");
            }

        } else if (requestCode == EDIT_BOOK_REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null) {
                Book book = new Book();
                book.setCategoryId(selectedCategoryId);
                book.setBookName(data.getStringExtra(AddAndEditActivity.BOOK_NAME));
                book.setUnitPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));
                book.setBookId(selectedBookId);
                mMainActivityViewModel.updateBook(book);
            } else {
                show("Something went wrong...");
            }

        }
    }
}
