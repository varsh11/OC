package fr.neamar.ocmaster.test;

import fr.neamar.ocmaster.BuildConfig;
import fr.neamar.ocmaster.MainActivity;
import fr.neamar.ocmaster.OCApplication;
import fr.neamar.ocmaster.DataHandler;

import static org.mockito.Mockito.mock;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {
    DataHandler mockDataHandler;

    @Before
    public void setUp() {
        mockDataHandler = mock(DataHandler.class);
        OCApplication.setDataHandler(mockDataHandler);
    }

    @Test
    public void testSomething() throws Exception {
        // TODO-add more tests...
        assertTrue(Robolectric.setupActivity(MainActivity.class) != null);
    }
}
