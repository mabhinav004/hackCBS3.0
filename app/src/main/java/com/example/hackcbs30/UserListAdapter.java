package com.example.hackcbs30;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {

        ArrayList<UserObject> userList;

        public UserListAdapter(ArrayList<UserObject> userList){
            this.userList = userList;
        }

        @NonNull
        @Override
        public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemuser, null, false);
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutView.setLayoutParams(lp);

            UserListViewHolder rcv = new UserListViewHolder(layoutView);
            return rcv;
        }

        @Override
        public void onBindViewHolder(@NonNull final UserListViewHolder holder, final int position) {
            holder.mName.setText(userList.get(position).getName());
            holder.mPhone.setText(userList.get(position).getAge());
            holder.mPhone.setText(userList.get(position).getBloodGroup());
            holder.mPhone.setText(userList.get(position).getGender());
            holder.mPhone.setText(userList.get(position).getNumber());
            notifyDataSetChanged();

            holder.mAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    userList.get(holder.getAdapterPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }



        class UserListViewHolder extends RecyclerView.ViewHolder{
            TextView mName, mPhone,mage,mBloodGroup,mGender;
            LinearLayout mLayout;
            CheckBox mAdd;
            UserListViewHolder(View view){
                super(view);
                mName = view.findViewById(R.id.Name);
                mPhone = view.findViewById(R.id.Number);
                mage=view.findViewById(R.id.Age);
                mBloodGroup=view.findViewById(R.id.BloodGroup);
                mGender=view.findViewById(R.id.Gender);
                mAdd = view.findViewById(R.id.add);
                mLayout = view.findViewById(R.id.layout);
            }
        }
    }

