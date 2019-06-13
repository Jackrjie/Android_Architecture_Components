package com.tim.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tim.bookstore.databinding.ActivityAddAndEditBinding;
import com.tim.bookstore.model.Book;

public class AddAndEditActivity extends AppCompatActivity {

    private Book mBook;
    public static final String BOOK_ID="bookId";
    public static final String BOOK_NAME="bookName";
    public static final String UNIT_PRICE="unitPrice";
    private ActivityAddAndEditBinding mBinding;
    private AddAndEditActivityClickHandlers mClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);

        mBook = new Book();
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_and_edit);
        mBinding.setBook(mBook);

        mClickHandlers = new AddAndEditActivityClickHandlers(this);
        mBinding.setClickHandler(mClickHandlers);

        Intent intent = getIntent();
        if (intent.hasExtra(BOOK_ID)){
            setTitle("Edit Book");
            mBook.setBookName(intent.getStringExtra(BOOK_NAME));
            mBook.setUnitPrice(intent.getStringExtra(UNIT_PRICE));
        } else {
            setTitle("Add New Book");
        }
    }

    public class AddAndEditActivityClickHandlers {
        Context mContext;

        public AddAndEditActivityClickHandlers(Context context) {
            mContext = context;
        }

        public void onSubmitButtonClicked(View view){
            if (mBook.getBookName() == null){
                show("Name field cannot be empty");
            } else {
                Intent i = new Intent();
                i.putExtra(BOOK_NAME,mBook.getBookName());
                i.putExtra(UNIT_PRICE,mBook.getUnitPrice());
                setResult(RESULT_OK,i);
                finish();
            }
        }
    }

    private void show(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
