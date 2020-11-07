package com.example.hackcbs30;

import android.content.Context;
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
        Context mcontext;

        public UserListAdapter(ArrayList<UserObject> userList,Context xt){
            this.userList = userList;
            this.mcontext=xt;
        }

        @NonNull
        @Override
        public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            //inflater.inflate(R.layout.itemuser,parent,false);

            View layoutView = inflater.inflate(R.layout.itemuser, parent, false);
            UserListViewHolder rcv = new UserListAdapter.UserListViewHolder(layoutView);
            return rcv;
        }

        @Override
        public void onBindViewHolder(@NonNull final UserListViewHolder holder, final int position) {
            holder.mName.setText(userList.get(position).getName());
            holder.mage.setText(userList.get(position).getAge());
            holder.mBloodGroup.setText(userList.get(position).getBloodGroup());
            holder.mGender.setText(userList.get(position).getGender());
            holder.mPhone.setText(userList.get(position).getNumber());

        }

        @Override
        public int getItemCount() {
            return userList.size();
        }



        public class UserListViewHolder extends RecyclerView.ViewHolder{
            TextView mName, mPhone,mage,mBloodGroup,mGender;
            LinearLayout mLayout;
            CheckBox mAdd;
           public  UserListViewHolder(View view){
                super(view);
                mName = view.findViewById(R.id.Name);
                mPhone = view.findViewById(R.id.Number);
                mage=view.findViewById(R.id.Age);
                mBloodGroup=view.findViewById(R.id.BloodGroup);
                mGender=view.findViewById(R.id.Gender);
                //mAdd = view.findViewById(R.id.add);
                //mLayout = view.findViewById(R.id.layout);
            }
        }
    }

