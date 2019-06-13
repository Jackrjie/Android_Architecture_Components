package com.tim.studentlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tim.studentlist.adapter.RvAppAdapter;
import com.tim.studentlist.db.AbStudentsAppDatabase;
import com.tim.studentlist.db.entity.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRvApp;
    private FloatingActionButton mFabAdd;
    private AbStudentsAppDatabase mDatabase;
    private List<Student> mStudentList;
    private RvAppAdapter mAdapter;
    private static final int NEW_STUDENT_REQUEST_CODE = 1;
    private static final int UPDATE_STUDENT_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup recyclerView
        mRvApp = findViewById(R.id.rv_app);
        mRvApp.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRvApp.setHasFixedSize(true);

        // setup adapter for recyclerView
        mAdapter = new RvAppAdapter(MainActivity.this,mStudentList);
        mRvApp.setAdapter(mAdapter);

        // initial database
        mDatabase = Room.databaseBuilder(getApplicationContext(),AbStudentsAppDatabase.class,"studentDB")
                .addCallback(mCallback)
                .build();
        loadData();

        // swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Student studentToDelete = mStudentList.get(viewHolder.getAdapterPosition());
                deleteStudent(studentToDelete);
                String msg = studentToDelete.getName() + " has been removed";
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mRvApp);

        mFabAdd = findViewById(R.id.fab_add);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddNewStudentActivity.class);
                intent.putExtra("ACTION","create");
                startActivityForResult(intent,NEW_STUDENT_REQUEST_CODE);
            }
        });
    }

    public void updateCurrentStudent(final Student student,final int position){
        String name,email,country;
        int index = position;
        Intent intent;
        if (student != null){
            name = student.getName();
            email = student.getEmail();
            country = student.getCountry();

            intent = new Intent(getApplicationContext(),AddNewStudentActivity.class);
            intent.putExtra("_NAME",name);
            intent.putExtra("_EMAIL",email);
            intent.putExtra("_COUNTRY",country);
            intent.putExtra("ACTION","update");
            intent.putExtra("POSITION",index);

            startActivityForResult(intent,UPDATE_STUDENT_REQUEST_CODE);
        }
    }

    private void loadData() {
        new GetAllStudentsAsyncTask().execute();
    }

    private class GetAllStudentsAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            mStudentList = mDatabase.getStudentDAO().getAllStudents();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAdapter.setStudentList(mStudentList);
        }
    }

    private void deleteStudent(Student student){
        new DeleteStudentAsyncTask().execute(student);
    }

    private class DeleteStudentAsyncTask extends AsyncTask<Student,Void,Void>{

        @Override
        protected Void doInBackground(Student... students) {
            mDatabase.getStudentDAO().deleteStudent(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    private void addNewStudent(Student student){
        new AddNewStudentAsyncTask().execute(student);
    }

    private class AddNewStudentAsyncTask extends AsyncTask<Student,Void,Void>{

        @Override
        protected Void doInBackground(Student... students) {
            mDatabase.getStudentDAO().addStudent(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    private void updateStudent(String name,String email,String country,int position){
        Student student = mStudentList.get(position);
        student.setName(name);
        student.setEmail(email);
        student.setCountry(country);
        new UpdateStudentAsyncTask().execute(student);
        mStudentList.set(position,student);
    }

    private class UpdateStudentAsyncTask extends AsyncTask<Student,Void,Void>{

        @Override
        protected Void doInBackground(Student... students) {
            mDatabase.getStudentDAO().updateStudent(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_STUDENT_REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null){
                String name = data.getStringExtra("NAME");
                String email = data.getStringExtra("EMAIL");
                String country = data.getStringExtra("COUNTRY");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE,dd MMM yyyy");
                String currentDate = simpleDateFormat.format(new Date());

                Student student = new Student();
                student.setName(name);
                student.setEmail(email);
                student.setCountry(country);
                student.setRegisteredDate(currentDate);

                addNewStudent(student);
            }
        }
        if (requestCode == UPDATE_STUDENT_REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null){
                String name = data.getStringExtra("NAME");
                String email = data.getStringExtra("EMAIL");
                String country = data.getStringExtra("COUNTRY");
                int position = data.getIntExtra("POSITION",0);

                Student student = new Student();
                student.setName(name);
                student.setEmail(email);
                student.setCountry(country);
                updateStudent(name,email,country,position);
            }
        }
    }

    RoomDatabase.Callback mCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            initStudents();
            addNewStudent(initStudents().get("Alex"));
            addNewStudent(initStudents().get("Bob"));
            addNewStudent(initStudents().get("Coc"));
        }
    };

    private Map<String, Student> initStudents(){
        // Alex
        Student alex = new Student();
        alex.setName("Alex");
        alex.setEmail("alex@gmail.com");
        alex.setCountry("Singapore");
        alex.setRegisteredDate("Mon, 03 Jun 2019");

        // Bob
        Student bob = new Student();
        bob.setName("Bob");
        bob.setEmail("bob@gmail.com");
        bob.setCountry("Singapore");
        bob.setRegisteredDate("Mon, 03 Jun 2019");

        // Coc
        Student coc = new Student();
        coc.setName("Coc");
        coc.setEmail("coc@gmail.com");
        coc.setCountry("Malaysia");
        coc.setRegisteredDate("Tue, 04 Jun 2019");

        Map<String,Student> studentMap = new HashMap<>();
        studentMap.put("Alex",alex);
        studentMap.put("Bob",bob);
        studentMap.put("Coc",coc);

        return studentMap;
    }

}
