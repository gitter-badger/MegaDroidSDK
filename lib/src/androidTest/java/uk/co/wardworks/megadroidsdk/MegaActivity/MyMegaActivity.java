package uk.co.wardworks.megadroidsdk.MegaActivity;

import android.os.Bundle;
import android.os.PersistableBundle;

import java.util.ArrayList;

import uk.co.wardworks.megadroidsdk.Activities.MegaActivity;

/**
 * Created by Gus on 22/10/2016.
 */
public class MyMegaActivity extends MegaActivity {

    public ArrayList<State> states = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        states.add(getState());
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        states.add(getState());
    }

    @Override
    protected void onStart() {
        super.onStart();
        states.add(getState());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        states.add(getState());
    }

    @Override
    protected void onResume() {
        super.onResume();
        states.add(getState());
    }

    @Override
    protected void onPause() {
        super.onPause();
        states.add(getState());
    }

    @Override
    protected void onStop() {
        super.onStop();
        states.add(getState());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        states.add(getState());
    }

}
