package uk.co.wardworks.megadroidsdk.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import static uk.co.wardworks.megadroidsdk.Activities.ActivityMega.State.*;
import static uk.co.wardworks.megadroidsdk.Activities.ActivityMega.Values.*;
import static uk.co.wardworks.megadroidsdk.Activities.ActivityMega.Keys.*;

/**
 * Created by Gus on 21/10/2016.
 */
public abstract class ActivityMega extends Activity {

    public final String TAG = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")+1);
    private State state = PRECEDING;

    public State getState(){

        return state;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processIntentExtras(getIntent());
        state = CREATED;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        processIntentExtras(getIntent());
        state = CREATED;
    }

    @Override
    protected void onStart() {
        super.onStart();
        state = STARTED;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        state = RESUMED;
    }

    @Override
    protected void onPause() {
        super.onPause();
        state = PAUSED;
    }

    @Override
    protected void onStop() {
        super.onStop();
        state = STOPPED;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        state = DESTROYED;
    }

    private void processIntentExtras(Intent intent){

        finishActivity(intent.getBooleanExtra(FINISH_APP, false));
        setDecorMode(intent.getIntExtra(DECOR_MODE, -1));

    }

    private void finishActivity(boolean finish){

        if(finish){
            finish();
        }

    }

    protected void setDecorMode(int mode){

        switch(mode){

            case IMMERSIVE:
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE
                );
                break;
            case DIM:
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LOW_PROFILE
                );
                break;
            case FULLSCREEN:
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN
                );
                break;
            case FULLSCREEN_NO_NAV:
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                );
                break;
            default:
                break;

        }

    }

    public boolean isVisible(){

        return state == STARTED || state == RESUMED;

    }

    public enum State{

        PRECEDING,
        CREATED,
        STARTED,
        RESUMED,
        PAUSED,
        STOPPED,
        DESTROYED

    }

    public static class Keys{

        public static final String DECOR_MODE = "decor";
        public static final String FINISH_APP = "finish";

    }

    public static class Values{

        public static final int IMMERSIVE = 0;
        public static final int DIM = 1;
        public static final int FULLSCREEN = 2;
        public static final int FULLSCREEN_NO_NAV = 3;

    }

}
