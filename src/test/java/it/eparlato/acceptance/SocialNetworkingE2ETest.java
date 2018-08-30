package it.eparlato.acceptance;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SocialNetworkingE2ETest {

    @Test
    public void should_return_an_error_message_if_I_just_write_a_user_name() throws UnsupportedEncodingException {

        String command = "Alice";
        ByteArrayInputStream input = new ByteArrayInputStream(command.getBytes("UTF-8"));

        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        PrintStream productionSystemOut = System.out;
        PrintStream hijackedSystemOut = new PrintStream(canvas);
        System.setOut(hijackedSystemOut);

        SocialNetworkingApp socialNetworkingApp = new SocialNetworkingApp(input, System.out);

        socialNetworkingApp.run();

        String expected = "User Alice doesn't exist" + System.getProperty("line.separator");

        assertEquals(expected, canvas.toString("UTF-8"));


        System.setOut(productionSystemOut);
    }
}
