package com.tim.bookstore.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.tim.bookstore.model.Book;

import java.util.ArrayList;

/**
 * create by Tim on 6/8/2019
 * email: jackrjie@gmail.com
 */
public class BooksDiffCallback extends DiffUtil.Callback {
    ArrayList<Book> mOldBooksList;
    ArrayList<Book> mNewBooksList;

    public BooksDiffCallback(ArrayList<Book> oldBooksList, ArrayList<Book> newBooksList) {
        mOldBooksList = oldBooksList;
        mNewBooksList = newBooksList;
    }

    @Override
    public int getOldListSize() {
        return mOldBooksList == null ? 0 : mOldBooksList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewBooksList == null ? 0 : mNewBooksList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldBooksList.get(oldItemPosition).getBookId() == mNewBooksList.get(newItemPosition).getBookId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldBooksList.get(oldItemPosition).equals(mNewBooksList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);

    }
}
