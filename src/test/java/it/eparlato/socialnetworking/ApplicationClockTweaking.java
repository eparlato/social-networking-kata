package it.eparlato.socialnetworking;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ApplicationClockTweaking {
    private final long ONE_MINUTE = 60 * 1000;
    private final long ONE_SECOND = 1000;

    long now;
    TweakedApplicationClock applicationClock;

    @Before
    public void setup() {
        now = System.currentTimeMillis();
        applicationClock = new TweakedApplicationClock(now);
    }

    @Test
    public void a_tweaked_clock_should_be_able_to_subtract_five_minutes_from_current_time() {
        long fiveMinutesAgo = now - (5 * ONE_MINUTE);
        applicationClock.subtractMinutes(5);

        assertEquals(fiveMinutesAgo, applicationClock.currentTimeMillis());
    }

    @Test
    public void a_tweaked_clock_should_be_able_to_subtract_fifteen_second_from_current_time() {
        long fifteenSecondsAgo = now - (15 * ONE_SECOND);
        applicationClock.subtractSeconds(15);

        assertEquals(fifteenSecondsAgo, applicationClock.currentTimeMillis());
    }

    @Test
    public void a_tweaked_clock_should_be_able_to_subtract_minutes_and_seconds_from_current_time() {
        long fiftyHeightMinutesAndThirtySecondsAgo = now - (58 * ONE_MINUTE) - (30 * ONE_SECOND);
        applicationClock.subtractMinutes(58).subtractSeconds(30);

        assertEquals(fiftyHeightMinutesAndThirtySecondsAgo, applicationClock.currentTimeMillis());
    }

    @Test
    public void a_tweaked_clock_should_be_able_to_override_existing_time() {
        applicationClock.subtractMinutes(5);

        applicationClock.setCurrentTimeMillis(now);

        assertEquals(now, applicationClock.currentTimeMillis());
    }
}
