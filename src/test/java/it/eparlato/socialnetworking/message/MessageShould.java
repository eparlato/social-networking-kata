package it.eparlato.socialnetworking.message;

import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MessageShould {
    long now;
    Message message;

    @Before
    public void setup() {
        now = System.currentTimeMillis();
    }

    @Test
    public void write_that_a_message_is_15_seconds_old_if_timeline_is_required() {
        long fifteenSecondsAgo = new TweakedApplicationClock(now).subtractSeconds(15).currentTimeMillis();

        message = new Message("I'm in New York today! Anyone wants to have a coffee?", fifteenSecondsAgo);

        assertEquals("I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)", message.formatForViewTimelineStartingFromTime(now));
    }

    @Test
    public void write_that_a_message_is_3_minutes_old_if_timeline_is_required() {
        long threeMinutesAgo = new TweakedApplicationClock(now).subtractMinutes(3).currentTimeMillis();

        message = new Message("I want a cup of coffee", threeMinutesAgo);

        assertEquals("I want a cup of coffee (3 minutes ago)", message.formatForViewTimelineStartingFromTime(now));
    }

    @Test
    public void write_a_singular_minute_message_if_the_message_is_one_minute_old() {
        long oneMinuteAgo = new TweakedApplicationClock(now).subtractMinutes(1).currentTimeMillis();

        message = new Message("I want a cup of tea", oneMinuteAgo);

        assertEquals("I want a cup of tea (1 minute ago)", message.formatForViewTimelineStartingFromTime(now));
    }

    @Test
    public void write_a_singular_second_message_if_the_message_is_one_second_old() {
        long oneSecondAgo = new TweakedApplicationClock(now).subtractSeconds(1).currentTimeMillis();

        message = new Message("I feel good", oneSecondAgo);

        assertEquals("I feel good (1 second ago)", message.formatForViewTimelineStartingFromTime(now));
    }
}
