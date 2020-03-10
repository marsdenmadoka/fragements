package com.madoka.fragements;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Workout.workouts[i].getName();//Create a String array of the workout names.
        }

        //Create an array adapter.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(

                inflater.getContext(), android.R.layout.simple_list_item_1, names); //Get the context from the layout inflater.
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    static interface WorkoutListListener {
        void itemClicked(long id);
    }
    private WorkoutListListener listener; //Add the listener to the fragment.

    @Override
    public void onAttach(Activity activity) { //called when fragment is attached to the main activity
        super.onAttach(activity);
        this.listener = (WorkoutListListener) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) { //Tell the listener when an item in the ListView is clicked.
        if (listener != null) {

            listener.itemClicked(id);

        }
    }
}
