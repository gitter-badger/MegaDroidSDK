package uk.co.wardworks.megadroidsdk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import uk.co.wardworks.megadroidsdk.Activities.TestMegaActivity;

import static uk.co.wardworks.megadroidsdk.Activities.MegaActivity.State.PRECEDING;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class RoboUnitTests {

    @Test
    public void testMegaActivity() throws Exception {

        TestMegaActivity testMegaActivity = new TestMegaActivity();

        assertEquals("TestMegaActivity", testMegaActivity.TAG);

        assertEquals(PRECEDING, testMegaActivity.getState());

        assertEquals(false, testMegaActivity.isVisible());
    }

}