package it.eparlato.socialnetworking.message;

import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MessageShould {
    @Test
    public void write_that_a_message_is_15_seconds_old_if_reading_is_required() {
        long now = System.currentTimeMillis();
        long fifteenSecondsAgo = new TweakedApplicationClock(now).subtractSeconds(15).currentTimeMillis();

        Message message = new Message("I'm in New York today! Anyone wants to have a coffee?", fifteenSecondsAgo);

        assertEquals("I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)", message.readingText(now));
    }

    @Test
    public void write_that_a_message_is_3_minutes_old_if_reading_required() {
        long now = System.currentTimeMillis();
        long threeMinutesAgo = new TweakedApplicationClock(now).subtractMinutes(3).currentTimeMillis();

        Message message = new Message("I want a cup of coffee", threeMinutesAgo);

        assertEquals("I want a cup of coffee (3 minutes ago)", message.readingText(now));
    }

    @Test
    public void write_a_proper_message_if_the_message_is_one_minute_old() {
        long now = System.currentTimeMillis();
        long threeMinutesAgo = new TweakedApplicationClock(now).subtractMinutes(1).currentTimeMillis();

        Message message = new Message("I want a cup of tea", threeMinutesAgo);

        assertEquals("I want a cup of tea (1 minute ago)", message.readingText(now));
    }

    @Test
    public void write_a_proper_message_if_the_message_is_one_second_old() {
        long now = System.currentTimeMillis();
        long threeMinutesAgo = new TweakedApplicationClock(now).subtractSeconds(1).currentTimeMillis();

        Message message = new Message("I feel good", threeMinutesAgo);

        assertEquals("I feel good (1 second ago)", message.readingText(now));
    }
}
