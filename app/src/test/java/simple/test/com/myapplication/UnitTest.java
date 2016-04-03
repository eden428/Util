package simple.test.com.myapplication;

import com.eden.common.util.file.WordCountUtils;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class UnitTest {
    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testCountWords() throws Exception {
        String s = "my name is eden";
        Assert.assertEquals(4, WordCountUtils.count(s).getWordCount());
    }
}