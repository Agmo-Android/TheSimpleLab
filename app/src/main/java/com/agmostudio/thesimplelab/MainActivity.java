package com.agmostudio.thesimplelab;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    String team = "team_a";
    TextView countDown;
    Button clickMeButton;
    Button team_a_button;
    Button team_b_button;
    int point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countDown = (TextView) findViewById(R.id.count_down);
        clickMeButton = (Button) findViewById(R.id.click_me);
        team_a_button = (Button) findViewById(R.id.team_a);
        team_b_button = (Button) findViewById(R.id.team_b);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("event");
        myRef.addValueEventListener(new ValueEventListener() {
            // dataSnaphot is where the data coming back
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                // (Long) dataSnapshot.child("eventStartTime").getValue() is a event time;
                long eventTime = (Long) dataSnapshot.child("eventStartTime").getValue();
                // System.currentTimeMillis() is your current device time.
                long deviceTime = System.currentTimeMillis();

                // Now, if "Event Time" is bigger then my "Devices time",
                // which mean your event haven't expiry yet,
                // then let start the count down timer
                if (eventTime > deviceTime) {

                    // Disable button Click, prevent player to click the button when event has set.
                    clickMeButton.setEnabled(false);

                    // eventTime - deviceTime, is to calculate what long is the time i have to start the time
                    // and set into count down, to do the timer count down.
                    // Example : eventTime is "1491704194950" and DeviceTime is "1491704174950"
                    // 1491704194950(EventTime) - 1491704174950(DeviceTime) = 20000(Millis)
                    // convert 20000(Millis) to second is 20000/1000 become 20 Second
                    // So 1000 equal to 1 Second
                    long eventTimeToStart = eventTime - deviceTime;
                    new CountDownTimer(eventTimeToStart, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            // set the count down for event count down on 1 second
                            // millisUntilFinished / 1000 ,
                            // if millisUntilFinished is 10000, convert into second is 10
                            // so the calculation of this is millis/1000 become a second
                            countDown.setText("Start in : " + millisUntilFinished / 1000);
                        }

                        @Override
                        public void onFinish() {
                            // Set your game point to 0
                            point = 0;

                            // when count down is finished
                            // let play the game !!
                            // enable the button now !!
                            clickMeButton.setEnabled(true);


                            // Now We need to set another count down timer to finish the game !! By using CountDownTimer again
                            // (Long) dataSnapshot.child("duration").getValue() is the duration of the game.
                            new CountDownTimer((Long) dataSnapshot.child("duration").getValue(), 1000){
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    // This time we need the millis count down for end time,
                                    // same concept for count down for start time
                                    countDown.setText("End in : " + millisUntilFinished / 1000);
                                }

                                @Override
                                public void onFinish() {
                                    // Set your final score to view
                                    countDown.setText("Your score : " + point);
                                    // Tell this game is finished, set to click me button view
                                    clickMeButton.setText("Game is Finish");
                                    // Disable the click because is finished
                                    clickMeButton.setEnabled(false);
                                    // get team_a data from server
                                    database.getReference("team_a").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.
                                            team_a_button.setText("Team a has " + dataSnapshot.getChildrenCount());
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            // Failed to read value
                                        }
                                    });

                                    // get team_b data from server
                                    database.getReference("team_b").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.
                                            team_b_button.setText("Team b has " + dataSnapshot.getChildrenCount());
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            // Failed to read value
                                        }
                                    });
                                }
                            }.start();
                        }
                    }.start();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        team_a_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast is show a message
                // Toast.LENGTH_SHORT is a duration,
                // Toast.LENGTH_SHORT is 2 second
                // Toast.LENGTH_LONG is 3 second
                Toast.makeText(MainActivity.this, "Selected Team A", Toast.LENGTH_SHORT).show();
                // change your support team to team a
                team = "team_a";
            }
        });

        team_b_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Selected Team B", Toast.LENGTH_SHORT).show();
                // change your support team to team b
                team = "team_b";
            }
        });

        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // "++" mean increasing 1 point
                point++;
                // Set your point into your click view
                clickMeButton.setText("Point : " + point);
                // Start push your support team to server from here
                // UUID is a unique id, but we use this as a one count.
                database.getReference(team).push().setValue(UUID.randomUUID().toString());
            }
        });
    }
}