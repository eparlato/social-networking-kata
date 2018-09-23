package it.eparlato.socialnetworking.acceptance;

import it.eparlato.socialnetworking.SocialNetworkProcessor;
import it.eparlato.socialnetworking.command.SimpleCommandFactory;
import it.eparlato.socialnetworking.parser.RegexInputParser;
import it.eparlato.socialnetworking.TweakedApplicationClock;
import it.eparlato.socialnetworking.user.repository.InMemoryUserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;

public class SocialNetworkingApplicationFeatures {
    final String LINE_SEPARATOR = System.getProperty("line.separator");
    ByteArrayOutputStream canvas = new ByteArrayOutputStream();
    PrintStream originalSystemOut = System.out;
    PrintStream hijackedSystemOut = new PrintStream(canvas);
    SocialNetworkProcessor socialNetworkProcessor;

    long now;
    TweakedApplicationClock applicationClock;

    @Before
    public void setup() {
        // Standard output hijacking
        System.setOut(hijackedSystemOut);

        now = System.currentTimeMillis();
        applicationClock = new TweakedApplicationClock(now);

        socialNetworkProcessor = new SocialNetworkProcessor(
                new RegexInputParser(new SimpleCommandFactory(new InMemoryUserRepository(), applicationClock)));
    }

    @After
    public void tearDown() {
        // Restore the original system.out
        System.setOut(originalSystemOut);
    }

    @Test
    public void shows_the_user_timeline_when_the_user_name_is_typed() throws UnsupportedEncodingException {
        applicationClock.subtractMinutes(5);
        socialNetworkProcessor.process("Alice -> I love the weather today");

        applicationClock.resetAndSubtractMinutes(2);
        socialNetworkProcessor.process("Bob -> Damn! We lost!");

        applicationClock.resetAndSubtractMinutes(1);
        socialNetworkProcessor.process("Bob -> Good game though.");

        applicationClock.setCurrentTimeMillis(now);
        socialNetworkProcessor.process("Alice");
        socialNetworkProcessor.process("Bob");

        String expected =
                "I love the weather today (5 minutes ago)" + LINE_SEPARATOR +
                        "Good game though. (1 minute ago)" + LINE_SEPARATOR +
                        "Damn! We lost! (2 minutes ago)" + LINE_SEPARATOR;

        assertEquals(expected, canvas.toString("UTF-8"));
    }

    @Test
    public void lets_us_subscribe_to_a_user_timeline_and_view_a_list_of_all_subscriptions() throws UnsupportedEncodingException {
        applicationClock.subtractMinutes(5);
        socialNetworkProcessor.process("Alice -> I love the weather today");

        applicationClock.resetAndSubtractMinutes(2);
        socialNetworkProcessor.process("Bob -> Damn! We lost!");

        applicationClock.resetAndSubtractMinutes(1);
        socialNetworkProcessor.process("Bob -> Good game though.");

        applicationClock.resetAndSubtractSeconds(15);
        socialNetworkProcessor.process("Charlie -> I'm in New York today! Anyone wants to have a coffee?");

        applicationClock.setCurrentTimeMillis(now);
        socialNetworkProcessor.process("Charlie follows Alice");
        socialNetworkProcessor.process("Charlie follows Bob");
        socialNetworkProcessor.process("Charlie wall");

        String expected =
                "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)" + LINE_SEPARATOR +
                        "Bob - Good game though. (1 minute ago)" + LINE_SEPARATOR +
                        "Bob - Damn! We lost! (2 minutes ago)" + LINE_SEPARATOR +
                        "Alice - I love the weather today (5 minutes ago)" + LINE_SEPARATOR;

        assertEquals(expected, canvas.toString("UTF-8"));
    }

}
