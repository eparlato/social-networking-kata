package it.eparlato.socialnetworking.message;

import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MessageFormatting {
    private static final String UNKNOWN_USER = "unknown";
    long now;
    Message message;

    @Before
    public void setup() {
        now = System.currentTimeMillis();
    }

    @Test
    public void a_message_should_write_how_many_seconds_is_old() {
        long fifteenSecondsAgo = new TweakedApplicationClock(now).subtractSeconds(15).currentTimeMillis();

        message = new Message("I'm in New York today! Anyone wants to have a coffee?", fifteenSecondsAgo, UNKNOWN_USER);

        assertEquals("I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)",
                message.formatForViewTimelineStartingFrom(now));
    }

    @Test
    public void a_message_should_write_how_many_minutes_is_old() {
        long threeMinutesAgo = new TweakedApplicationClock(now).subtractMinutes(3).currentTimeMillis();

        message = new Message("I want a cup of coffee", threeMinutesAgo, UNKNOWN_USER);

        assertEquals("I want a cup of coffee (3 minutes ago)", message.formatForViewTimelineStartingFrom(now));
    }

    @Test
    public void a_message_should_write_minute_if_it_is_one_minute_old() {
        long oneMinuteAgo = new TweakedApplicationClock(now).subtractMinutes(1).currentTimeMillis();

        message = new Message("I want a cup of tea", oneMinuteAgo, UNKNOWN_USER);

        assertEquals("I want a cup of tea (1 minute ago)", message.formatForViewTimelineStartingFrom(now));
    }

    @Test
    public void a_message_should_write_second_if_it_is_one_second_old() {
        long oneSecondAgo = new TweakedApplicationClock(now).subtractSeconds(1).currentTimeMillis();

        message = new Message("I feel good", oneSecondAgo, UNKNOWN_USER);

        assertEquals("I feel good (1 second ago)", message.formatForViewTimelineStartingFrom(now));
    }

    @Test
    public void a_message_should_write_the_username_followed_by_the_text_and_the_time_elapsed_if_wall_format_is_called() {
        long sixMinutesAgo = new TweakedApplicationClock(now).subtractMinutes(6).currentTimeMillis();

        message = new Message("I feel like a coffee...", sixMinutesAgo, "Alice");

        assertEquals("Alice - I feel like a coffee... (6 minutes ago)", message.formatForWallStartingFrom(now));
    }
}
