package com.example.hackcbs30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    private RecyclerView mUserList;
    private RecyclerView.Adapter mUserListAdapter;
    private LinearLayoutManager mUserListLayoutManager;
    ArrayList<UserObject> userList;
        ArrayList<MobileContactObject>contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        userList=new ArrayList<>();
        contactList=new ArrayList<>();
      /*  UserObject obj=new UserObject("65652","dsadsa","dsdas","sdasdas","sadsa");
        UserObject obj1=new UserObject("65652","dsadsa","dsdas","sdasdas","sadsa");
        UserObject obj2=new UserObject("65652","dsadsa","dsdas","sdasdas","sadsa");
        UserObject obj3=new UserObject("65652","dsadsa","dsdas","sdasdas","sadsa");
        UserObject obj4=new UserObject("65652","dsadsa","dsdas","sdasdas","sadsa");
        UserObject obj5=new UserObject("65652","dsadsa","dsdas","sdasdas","sadsa");*/
        /*userList.add(obj);
        userList.add(obj1);
        userList.add(obj2);
        userList.add(obj3);
        userList.add(obj4);
        userList.add(obj5);*/

        //mUserListAdapter.notifyDataSetChanged();
        initializeRecyclerView();
        GetContactList();
       // getContactList();
    }
    private void GetContactList() {
        String ISOPrefix = getCountryISO();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phone = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phone = phone.replace(" ", "");
                        phone = phone.replace("-", "");
                        phone = phone.replace("(", "");
                        phone = phone.replace(")", "");

                        if(!String.valueOf(phone.charAt(0)).equals("+"))
                            phone = ISOPrefix + phone;

                        MobileContactObject mContact = new MobileContactObject("", name, phone);
                        contactList.add(mContact);
                        getUserDetails(mContact);
                        Log.i("saketTopper", "Name: " + name);
                        Log.i("saketTopper", "Phone Number: " + phone);
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }
    }
    private void getUserDetails(MobileContactObject mContact) {
        DatabaseReference mUserDB = FirebaseDatabase.getInstance().getReference().child("user");
        Query query = mUserDB.orderByChild("phone").equalTo(mContact.getPhone());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String phone = "";
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        if (childSnapshot.child("phone").getValue() != null)
                            phone = childSnapshot.child("phone").getValue().toString();
                        UserObject mUser = new UserObject(phone);
                        for (MobileContactObject mContactIterator : contactList) {
                            if (mContactIterator.getPhone().equals(mUser.getNumber())) {
                                mUser.setName(mContactIterator.getName());
                            }
                            userList.add(mUser);
                            mUserListAdapter.notifyDataSetChanged();
                            return;
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String getCountryISO() {
        String iso = null;

        TelephonyManager telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        if(telephonyManager.getNetworkCountryIso()!=null)
            if (!telephonyManager.getNetworkCountryIso().toString().equals(""))
                iso = telephonyManager.getNetworkCountryIso().toString();

        return CountryTwoPhonePrefix.getPhone(iso);
    }

    private void initializeRecyclerView() {

        mUserListAdapter = new UserListAdapter(userList,this);
        mUserList= findViewById(R.id.userList);
        mUserList.setAdapter(mUserListAdapter);
        mUserListLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        mUserList.setLayoutManager(mUserListLayoutManager);

    }
}