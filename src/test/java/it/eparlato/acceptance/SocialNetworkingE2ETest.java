package it.eparlato.acceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class SocialNetworkingE2ETest {
    ByteArrayOutputStream canvas = new ByteArrayOutputStream();
    PrintStream originalSystemOut = System.out;
    PrintStream hijackedSystemOut = new PrintStream(canvas);
    String commands;

    @Before
    public void setup() {
        // Standard output hijacking
        System.setOut(hijackedSystemOut);
    }

    @After
    public void tearDown() {
        // Restore the original system.out
        System.setOut(originalSystemOut);
    }

    @Test
    public void should_return_an_error_message_if_I_just_write_a_user_name() throws UnsupportedEncodingException {
        commands = "Alice";

        SocialNetworkingApp socialNetworkingApp = new SocialNetworkingApp(getInputStreamFrom(commands), System.out);
        socialNetworkingApp.run();

        String expected = "User Alice doesn't exist" + System.getProperty("line.separator");

        assertEquals(expected, canvas.toString("UTF-8"));
    }

    private InputStream getInputStreamFrom(String commands) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(commands.getBytes("UTF-8"));
    }
}
