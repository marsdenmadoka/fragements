package com.madoka.fragements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 *
 */
//Activities need to be registered in AndroidManifest.xml, but fragments don’t.
public class WorkoutDetailFragment extends Fragment {


    private long workoutId; //This is the ID of the workout the user chooses.  Later, we’ll use it to set the values of fragment’s views with the workout details.


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong("workoutId"); //the onsaveInstancestate()method gets called before the fragments get destroyed when you rotate the screen
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false); //This tells Android which layout the fragmentuses (in this case, it’s fragment_workout_detail).
    }
    public void onStart() {
         super.onStart();
        View view = getView();//fragments arent views so we cant use the find ViewById instead we use the getView method()
        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("workoutId", workoutId);//save the value of the workoutid in the saved instancestate bundle before the
    }//fragment gets destroyed. We're retrieving it in the onCreateView() method.

    public void setWorkout(long id) { //This is a setter method for the workoutID. The activity will use this method to set the value of the workout ID.
        this.workoutId = id;
    }

}
