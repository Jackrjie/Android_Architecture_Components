package com.tim.bookstore.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * create by Tim on 6/7/2019
 * email: jackrjie@gmail.com
 */

@Database(entities = {Category.class,Book.class},version = 1,exportSchema = false)
public abstract class BooksDatabase extends RoomDatabase {

    public abstract ICategoryDAO mCategoryDAO();
    public abstract IBookDAO mBookDAO();

    private static BooksDatabase instance;

    public static synchronized BooksDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BooksDatabase.class,
                    "books_database")
                    .fallbackToDestructiveMigration()   // When changing the database, it will auto delete the table and recreate the table
                    .addCallback(sCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback sCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void,Void,Void>{

        private ICategoryDAO mCategoryDAO;
        private IBookDAO mBookDAO;

        public InitialDataAsyncTask(BooksDatabase booksDatabase) {
            mCategoryDAO = booksDatabase.mCategoryDAO();
            mBookDAO = booksDatabase.mBookDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Category category1 = new Category();
            category1.setCategoryName("物语系列");
            category1.setCategoryDescription("西尾维新");

            Category category2 = new Category();
            category2.setCategoryName("刀剑神域系列");
            category2.setCategoryDescription("川原砾");

            Category category3= new Category();
            category3.setCategoryName("青春猪头少年系列");
            category3.setCategoryDescription("鸭志田一");

            mCategoryDAO.insert(category1);
            mCategoryDAO.insert(category2);
            mCategoryDAO.insert(category3);

            Book book1 = new Book();
            book1.setBookName("化物语(上)");
            book1.setUnitPrice("$19.99");
            book1.setCategoryId(1);

            Book book2 = new Book();
            book2.setBookName("化物语(下)");
            book2.setUnitPrice("$19.99");
            book2.setCategoryId(1);

            Book book3 = new Book();
            book3.setBookName("伤物语");
            book3.setUnitPrice("$19.99");
            book3.setCategoryId(1);

            Book book4 = new Book();
            book4.setBookName("伪物语(上)");
            book4.setUnitPrice("$19.99");
            book4.setCategoryId(1);

            Book book5 = new Book();
            book5.setBookName("伪物语(下)");
            book5.setUnitPrice("$19.99");
            book5.setCategoryId(1);

            Book book6 = new Book();
            book6.setBookName("猫物语(黑)");
            book6.setUnitPrice("$24.99");
            book6.setCategoryId(1);

            Book book7 = new Book();
            book7.setBookName("猫物语(白)");
            book7.setUnitPrice("$24.99");
            book7.setCategoryId(1);

            Book book8 = new Book();
            book8.setBookName("艾恩葛朗特");
            book8.setUnitPrice("$19.99");
            book8.setCategoryId(2);

            Book book9 = new Book();
            book9.setBookName("黑衣剑士");
            book9.setUnitPrice("$19.99");
            book9.setCategoryId(2);

            Book book10 = new Book();
            book10.setBookName("心之温度");
            book10.setUnitPrice("$19.99");
            book10.setCategoryId(2);

            Book book11 = new Book();
            book11.setBookName("朝露的少女");
            book11.setUnitPrice("$19.99");
            book11.setCategoryId(2);

            Book book12 = new Book();
            book12.setBookName("红鼻子麋鹿");
            book12.setUnitPrice("$19.99");
            book12.setCategoryId(2);

            Book book13 = new Book();
            book13.setBookName("青春猪头少年不会梦到兔女郎学姐");
            book13.setUnitPrice("$29.99");
            book13.setCategoryId(3);

            Book book14 = new Book();
            book14.setBookName("青春猪头少年不会梦到小恶魔学妹");
            book14.setUnitPrice("$29.99");
            book14.setCategoryId(3);

            Book book15 = new Book();
            book15.setBookName("青春猪头少年不会梦到理性小魔女");
            book15.setUnitPrice("$29.99");
            book15.setCategoryId(3);

            Book book16 = new Book();
            book16.setBookName("青春猪头少年不会梦到恋姊俏偶像");
            book16.setUnitPrice("$29.99");
            book16.setCategoryId(3);

            Book book17 = new Book();
            book17.setBookName("青春猪头少年不会梦到娇怜看家妹");
            book17.setUnitPrice("$29.99");
            book17.setCategoryId(3);

            Book book18 = new Book();
            book18.setBookName("青春猪头少年不会梦到怀梦美少女");
            book18.setUnitPrice("$29.99");
            book18.setCategoryId(3);

            Book book19 = new Book();
            book19.setBookName("青春猪头少年不会梦到初恋美少女");
            book19.setUnitPrice("$29.99");
            book19.setCategoryId(3);

            Book book20 = new Book();
            book20.setBookName("青春猪头少年不会梦到娇怜外出妹");
            book20.setUnitPrice("$29.99");
            book20.setCategoryId(3);

            Book book21= new Book();
            book21.setBookName("青春猪头少年不会梦到红书包女孩");
            book21.setUnitPrice("$29.99");
            book21.setCategoryId(3);

            mBookDAO.insert(book1);
            mBookDAO.insert(book2);
            mBookDAO.insert(book3);
            mBookDAO.insert(book4);
            mBookDAO.insert(book5);
            mBookDAO.insert(book6);
            mBookDAO.insert(book7);
            mBookDAO.insert(book8);
            mBookDAO.insert(book9);
            mBookDAO.insert(book10);
            mBookDAO.insert(book11);
            mBookDAO.insert(book12);
            mBookDAO.insert(book13);
            mBookDAO.insert(book14);
            mBookDAO.insert(book15);
            mBookDAO.insert(book16);
            mBookDAO.insert(book17);
            mBookDAO.insert(book18);
            mBookDAO.insert(book19);
            mBookDAO.insert(book20);
            mBookDAO.insert(book21);

            return null;
        }
    }

}
