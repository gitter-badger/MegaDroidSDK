package uk.co.wardworks.megadroidsdk;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLooper;

import java.util.Random;

import uk.co.wardworks.megadroidsdk.Threading.PeriodicTask;

import static uk.co.wardworks.megadroidsdk.Threading.PeriodicTask.INFINITE_LOOPS;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class IntervalTaskUnitTests {

    @Test
    public void testFiniteLoops() throws Exception {

        int loops = 5;
        int period = 1;
        IntervalTaskTestRunnable testRunnable = new IntervalTaskTestRunnable();

        PeriodicTask periodicTask = new PeriodicTask(
                testRunnable,
                period,
                loops,
                false
        );
        periodicTask.start();

        /* ensure count == loops after loops + 1 delayed tasks are run */
        for(int i = 0; i < loops+1; i++){

            ShadowLooper.runUiThreadTasksIncludingDelayedTasks();

        }

        Assert.assertEquals(loops, testRunnable.getCount());

    }

    @Test
    public void testInfiniteLoops() throws Exception {

        int expectedLoops = new Random().nextInt(100)+1;
        int period = 1;

        IntervalTaskTestRunnable testRunnable = new IntervalTaskTestRunnable();

        PeriodicTask periodicTask = new PeriodicTask(
                testRunnable,
                period,
                INFINITE_LOOPS,
                false
        );
        periodicTask.start();

        for(int i = 0; i < expectedLoops; i++){

            ShadowLooper.runUiThreadTasksIncludingDelayedTasks();

        }

        /* counter should increment exactly as many times as random expectedLoops value */
        Assert.assertEquals(expectedLoops, testRunnable.getCount());

    }

    private class IntervalTaskTestRunnable implements Runnable{

        private int count = 0;

        public int getCount(){

            return count;

        }

        @Override
        public void run() {

            count++;

        }
    }

}