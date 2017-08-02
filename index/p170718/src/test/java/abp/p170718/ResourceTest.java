package abp.p170718;

import java.io.File;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;

public class ResourceTest extends Assert {
    @Test
    public void testFileExists()
            throws Exception {
        URL resource = ResourceTest.class.getResource("TestFile");
        File file = new File(resource.toURI());
        assertTrue(file.exists());
    }
}
