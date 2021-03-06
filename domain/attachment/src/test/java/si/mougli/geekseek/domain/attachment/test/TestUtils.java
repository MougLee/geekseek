package si.mougli.geekseek.domain.attachment.test;

import java.net.MalformedURLException;
import java.net.URL;
import si.mougli.geekesek.domain.attachment.model.Attachment;

public final class TestUtils
{
    private TestUtils()
    {
    }

    public static Attachment createAttachment()
    {
        try
        {
            return new Attachment("Test Attachment", "text/plain", new URL("http://geekseek.org"));
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
