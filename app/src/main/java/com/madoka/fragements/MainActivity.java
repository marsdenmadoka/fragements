package com.madoka.fragements;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;//allows you to specif rrsorces for different screen sizes
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener{ //Implement the listener defined in WorkoutListFragment.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void itemClicked(long id) { //This method is defined in the listener. its an interface from WorkoutListFragment.java
        //Using fragment transactions
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
        WorkoutDetailFragment details = new WorkoutDetailFragment(); //Start the fragment transaction.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        details.setWorkout(id); //we only need to run this code if the frame layout is there
        ft.replace(R.id.fragment_container,details);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            startActivity(intent); //If the frame layout isn't there, the app must be running on a device with a smaller screen. Start
            //DetailActivity, passing it the ID of the workout.
        }
    }
}
