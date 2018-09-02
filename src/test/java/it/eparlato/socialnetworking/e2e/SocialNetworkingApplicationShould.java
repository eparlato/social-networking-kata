package it.eparlato.socialnetworking.e2e;

import it.eparlato.socialnetworking.SocialNetworkProcessor;
import it.eparlato.socialnetworking.SocialNetworkingApp;
import it.eparlato.socialnetworking.parser.ConcreteInputParser;
import it.eparlato.socialnetworking.parser.DummyInputParser;
import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import it.eparlato.socialnetworking.user.repository.InMemoryUserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class SocialNetworkingApplicationShould {
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
    public void return_an_error_message_if_I_just_write_a_user_name() throws UnsupportedEncodingException {
        commands = "Alice";

        SocialNetworkingApp socialNetworkingApp = new SocialNetworkingApp(getInputStreamFrom(commands), System.out, new DummyInputParser());
        socialNetworkingApp.run();

        String expected = "User Alice doesn't exist" + System.getProperty("line.separator");

        assertEquals(expected, canvas.toString("UTF-8"));
    }

    // TODO: this package is not going to be e2e anymore, but acceptance
    // remove the first tests, the e2e one. It's useless

    @Test
    public void return_the_user_timeline_when_a_user_name_is_typed() throws UnsupportedEncodingException {
        long now = System.currentTimeMillis();
        TweakedApplicationClock applicationClock = new TweakedApplicationClock(now);
        SocialNetworkProcessor socialNetworkProcessor = new SocialNetworkProcessor(
                new ConcreteInputParser(new InMemoryUserRepository(), applicationClock));

        applicationClock.subtractMinutes(2);
        socialNetworkProcessor.process("Bob -> Damn! We lost!");

        applicationClock.addMinutes(1);
        socialNetworkProcessor.process("Bob -> Good game though.");

        applicationClock.setCurrentTimeMillis(now);
        socialNetworkProcessor.process("Bob");

        String expected =
                "Good game though. (1 minute ago)" + System.getProperty("line.separator") +
                "Damn! We lost! (2 minutes ago)" + System.getProperty("line.separator");

        assertEquals(expected, canvas.toString("UTF-8"));
    }

    private InputStream getInputStreamFrom(String commands) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(commands.getBytes("UTF-8"));
    }
}
