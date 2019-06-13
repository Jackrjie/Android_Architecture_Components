package com.tim.studentlist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tim.studentlist.AddNewStudentActivity;
import com.tim.studentlist.MainActivity;
import com.tim.studentlist.R;
import com.tim.studentlist.db.entity.Student;

import java.util.List;

/**
 * create by Tim on 6/3/2019
 * email: jackrjie@gmail.com
 */
public class RvAppAdapter extends RecyclerView.Adapter<RvAppAdapter.RvViewHolder> {

    private List<Student> mStudentList;
    private MainActivity mMainActivity;

    public RvAppAdapter(MainActivity mainActivity,List<Student> studentList) {
        mMainActivity = mainActivity;
        mStudentList = studentList;
    }

    public class RvViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName,mTvEmail,mTvCountry,mTvDate;

        public RvViewHolder(@NonNull View view) {
            super(view);
            mTvName = view.findViewById(R.id.tv_name);
            mTvEmail = view.findViewById(R.id.tv_email);
            mTvCountry = view.findViewById(R.id.tv_country);
            mTvDate = view.findViewById(R.id.tv_date);
        }
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_rv,parent,false);
        return new RvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, final int position) {
        final Student currentStudent = mStudentList.get(position);
        holder.mTvName.setText(currentStudent.getName());
        holder.mTvEmail.setText(currentStudent.getEmail());
        holder.mTvCountry.setText(currentStudent.getCountry());
        holder.mTvDate.setText(currentStudent.getRegisteredDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.updateCurrentStudent(currentStudent,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mStudentList != null){
            return mStudentList.size();
        } else {
            return 0;
        }
    }

    // after have change on student list, invoke notifyDataSetChanged()
    public void setStudentList(List<Student> studentList) {
        mStudentList = studentList;
        notifyDataSetChanged();
    }
}
