package uk.co.wardworks.megadroidsdk.MegaActivity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

import static uk.co.wardworks.megadroidsdk.Activities.ActivityMega.State.CREATED;
import static uk.co.wardworks.megadroidsdk.Activities.ActivityMega.State.DESTROYED;
import static uk.co.wardworks.megadroidsdk.Activities.ActivityMega.State.PAUSED;
import static uk.co.wardworks.megadroidsdk.Activities.ActivityMega.State.RESUMED;
import static uk.co.wardworks.megadroidsdk.Activities.ActivityMega.State.STARTED;
import static uk.co.wardworks.megadroidsdk.Activities.ActivityMega.State.STOPPED;

/**
 * Created by Gus on 22/10/2016.
 */
public class ActivityMegaTest extends ActivityUnitTestCase<MyActivityMega> {

    MyActivityMega myMegaActivity;

    public ActivityMegaTest(){
        super(MyActivityMega.class);
    }

    public ActivityMegaTest(Class<MyActivityMega> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startActivity(new Intent(getInstrumentation().getTargetContext(), MyActivityMega.class), null, null);
        myMegaActivity = getActivity();
    }

    public void testStates() throws Exception {

        assertEquals(false, myMegaActivity.isVisible());
        myMegaActivity.onStart();
        assertEquals(true, myMegaActivity.isVisible());
        myMegaActivity.onResume();
        assertEquals(true, myMegaActivity.isVisible());
        myMegaActivity.onPause();
        assertEquals(false, myMegaActivity.isVisible());
        myMegaActivity.onStop();
        assertEquals(false, myMegaActivity.isVisible());
        myMegaActivity.onDestroy();
        assertEquals(false, myMegaActivity.isVisible());

        assertEquals(CREATED, myMegaActivity.states.get(0));
        assertEquals(STARTED, myMegaActivity.states.get(1));
        assertEquals(RESUMED, myMegaActivity.states.get(2));
        assertEquals(PAUSED, myMegaActivity.states.get(3));
        assertEquals(STOPPED, myMegaActivity.states.get(4));
        assertEquals(DESTROYED, myMegaActivity.states.get(5));


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
