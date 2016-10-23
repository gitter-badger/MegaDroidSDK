package uk.co.wardworks.megadroidsdk.Threading;

import android.os.Handler;
import android.os.Looper;

import static uk.co.wardworks.megadroidsdk.Threading.PeriodicTask.State.*;

/**
 * Created by Gus on 22/10/2016.
 */
public class PeriodicTask {

    public static final int INFINITE_LOOPS = -1;

    private Handler handler;
    private Runnable runnable;
    private int period;
    private int targetLoops;
    private int completedLoops;

    private boolean enabled = true;
    private State state = CREATED;

    public PeriodicTask(Runnable runnable, int period, boolean uiThread){

        this(runnable, period, INFINITE_LOOPS, uiThread);

    }

    public PeriodicTask(Runnable runnable, int period, int loops, boolean uiThread){

        this.runnable = runnable;
        this.period = period;
        this.targetLoops = loops;

        if(uiThread){

            this.handler = new Handler(Looper.getMainLooper());

        }else{

            this.handler = new Handler();

        }

    }

    public State getState(){

        return state;

    }

    public void start(){

        enabled = true;
        state = RUNNING;

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                /* check enabled after both task and delay, as both take time */
                if(!enabled){

                    state = ENDED;
                    return;

                }

                runnable.run();
                completedLoops++;

                if(enabled &&
                        (targetLoops == INFINITE_LOOPS || completedLoops < targetLoops)){

                    handler.postDelayed(this, period);

                }

                state = ENDED;

            }

        }, period);

    }

    public void stop(){

        enabled = false;

    }

    public enum State{

        CREATED,
        RUNNING,
        ENDED

    }

}
