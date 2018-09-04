package it.eparlato.socialnetworking.acceptance;

import it.eparlato.socialnetworking.SocialNetworkProcessor;
import it.eparlato.socialnetworking.parser.ConcreteInputParser;
import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import it.eparlato.socialnetworking.user.repository.InMemoryUserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;

public class SocialNetworkingApplicationFeatures {
    ByteArrayOutputStream canvas = new ByteArrayOutputStream();
    PrintStream originalSystemOut = System.out;
    PrintStream hijackedSystemOut = new PrintStream(canvas);


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
    public void shows_the_user_timeline_when_the_user_name_is_typed() throws UnsupportedEncodingException {
        long now = System.currentTimeMillis();
        TweakedApplicationClock applicationClock = new TweakedApplicationClock(now);
        SocialNetworkProcessor socialNetworkProcessor = new SocialNetworkProcessor(
                new ConcreteInputParser(new InMemoryUserRepository(), applicationClock));

        applicationClock.subtractMinutes(5);
        socialNetworkProcessor.process("Alice -> I love the weather today");

        applicationClock.addMinutes(3);
        socialNetworkProcessor.process("Bob -> Damn! We lost!");

        applicationClock.addMinutes(1);
        socialNetworkProcessor.process("Bob -> Good game though.");

        applicationClock.setCurrentTimeMillis(now);
        socialNetworkProcessor.process("Alice");
        socialNetworkProcessor.process("Bob");

        String expected =
                "I love the weather today (5 minutes ago)" + System.getProperty("line.separator") +
                "Good game though. (1 minute ago)" + System.getProperty("line.separator") +
                        "Damn! We lost! (2 minutes ago)" + System.getProperty("line.separator");

        assertEquals(expected, canvas.toString("UTF-8"));
    }

    @Test
    public void lets_us_subscribe_to_a_user_timeline_and_view_a_list_of_all_subscriptions() throws UnsupportedEncodingException {
        long now = System.currentTimeMillis();
        TweakedApplicationClock applicationClock = new TweakedApplicationClock(now);
        SocialNetworkProcessor socialNetworkProcessor = new SocialNetworkProcessor(
                new ConcreteInputParser(new InMemoryUserRepository(), applicationClock));

        applicationClock.subtractMinutes(5);
        socialNetworkProcessor.process("Alice -> I love the weather today");

        applicationClock.setCurrentTimeMillis(now);
        applicationClock.subtractMinutes(2);
        socialNetworkProcessor.process("Bob -> Damn! We lost!");

        applicationClock.setCurrentTimeMillis(now);
        applicationClock.subtractMinutes(1);
        socialNetworkProcessor.process("Bob -> Good game though.");

        applicationClock.setCurrentTimeMillis(now);
        applicationClock.subtractSeconds(15);
        socialNetworkProcessor.process("Charlie -> I'm in New York today! Anyone wants to have a coffee?");

        applicationClock.setCurrentTimeMillis(now);
        socialNetworkProcessor.process("Charlie follows Alice");
        socialNetworkProcessor.process("Charlie follows Bob");
        socialNetworkProcessor.process("Charlie wall");

        String expected =
                "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)" + System.getProperty("line.separator") +
                 "Bob - Good game though. (1 minute ago)" + System.getProperty("line.separator") +
                 "Bob - Damn! We lost! (2 minutes ago)" + System.getProperty("line.separator") +
                 "Alice - I love the weather today (5 minutes ago)" + System.getProperty("line.separator");

        assertEquals(expected, canvas.toString("UTF-8"));
    }
}
