package com.tim.bookstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tim.bookstore.R;
import com.tim.bookstore.databinding.BookListItemBinding;
import com.tim.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * create by Tim on 6/8/2019
 * email: jackrjie@gmail.com
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder>{

    private OnItemClickListener mListener;
    private ArrayList<Book> mBookList = new ArrayList<>();

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.book_list_item,parent,false);
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = mBookList.get(position);
        holder.mBookListItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    public void setBookList(ArrayList<Book> newBooksList) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new BooksDiffCallback(mBookList,newBooksList),false);
        mBookList = newBooksList;
        result.dispatchUpdatesTo(BooksAdapter.this);
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private BookListItemBinding mBookListItemBinding;

        public BookViewHolder(@NonNull BookListItemBinding bookListItemBinding) {
            super(bookListItemBinding.getRoot());
            mBookListItemBinding = bookListItemBinding;
            mBookListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();

                    if (mListener != null && clickedPosition!=RecyclerView.NO_POSITION) {
                        mListener.onItemClick(mBookList.get(clickedPosition));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Book book);
    }

    public void setListener(OnItemClickListener listener){
        mListener = listener;
    }
}


