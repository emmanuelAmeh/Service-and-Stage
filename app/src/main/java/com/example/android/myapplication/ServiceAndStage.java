package com.example.android.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ServiceAndStage extends AppCompatActivity {
    ArrayList<Notification> notifcationArrayList;
    ArrayList<String> programArrayList;
    //Declaring views
    private Button mBtnNotifSend;
    private RadioButton mRbStage;
    private RadioButton mRbHouse;
    private RadioButton mRbProgram;
    private EditText mEtNotification;
    private ListView lvProgram;
    private NotificationAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private CustomListAdapter mCustomListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_and_stage);

        //linking views to xml
        mBtnNotifSend = findViewById(R.id.sendNotifButton);
        mRbStage = findViewById(R.id.rb_stage);
        mRbHouse = findViewById(R.id.rb_house);
        mRbProgram = findViewById(R.id.rb_program);
        mEtNotification = findViewById(R.id.edittext_notif);

        notifcationArrayList = new ArrayList<>();
        programArrayList = new ArrayList<>();

        //setting actions

        notifcationArrayList.add(new Notification("Attention: Stage Mix,", "We are good to go"));
        notifcationArrayList.add(new Notification("Attention: House Mix,", "We are good to go too"));
        programArrayList.add("Opening Prayer");
        programArrayList.add("Praise Worship");
        programArrayList.add("Word");
        programArrayList.add("Closing Prayer");


        //set recycler view
        buildRecyclerView();

        recyclerDeleteOnSwipe();


        ListView listView = findViewById(R.id.listview);
        mCustomListAdapter = new CustomListAdapter(this, programArrayList);
        listView.setAdapter(mCustomListAdapter);


        mBtnNotifSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //close keyboard
                closeKeyboard(view);
                String message = mEtNotification.getText().toString();


                if (TextUtils.isEmpty(message.trim())) {
                    Snackbar.make(view, "Please type a message", Snackbar.LENGTH_SHORT).show();
                    return;
                } else {

                    sendMessage(message);

                    //testing ShareCompact
              /*      ShareCompat.IntentBuilder.from(ServiceAndStage.this)
                            .setChooserTitle("Sedding something")
                            .setType("text/plain")
                            .setText("This is just a practise")
                            .startChooser();*/

                }
            }
        });
    }

    //to implement delete on swipe in the recycler view
    private void recyclerDeleteOnSwipe() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                notifcationArrayList.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    //to build the recycler view
    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.notif_recyclerview);
        mRecyclerView.hasFixedSize();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new NotificationAdapter(notifcationArrayList);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    //to close soft keyboard when done typing
    private void closeKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*Check where to post the message
     * @param message message to be sent
     * */
    private void sendMessage(String message) {
        if (mRbStage.isChecked()) {
            notifcationArrayList.add(new Notification("Attention: Stage Mix,", message));
            mAdapter.notifyDataSetChanged();
            mEtNotification.setText("");
            mRecyclerView.smoothScrollToPosition(notifcationArrayList.size() - 1);
        } else if (mRbHouse.isChecked()) {
            notifcationArrayList.add(new Notification("Attention: House Mix,", message));
            mAdapter.notifyDataSetChanged();
            mEtNotification.setText("");
            mRecyclerView.smoothScrollToPosition(notifcationArrayList.size() - 1);
        } else {
            programArrayList.add(message);
            mCustomListAdapter.notifyDataSetChanged();

        }
    }


}


