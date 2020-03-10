package ru.scancode.serial;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class RobolectricTests {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void helloWorld() throws Exception{

        //Activity activity = Robolectric.buildActivity(LogoActivity.class).create().resume().get();
        TextView tv = activity.findViewById(R.id.serial_old_tv);
        assertEquals("Build.SERIAL: unknown",tv.getText().toString());
    }
}
