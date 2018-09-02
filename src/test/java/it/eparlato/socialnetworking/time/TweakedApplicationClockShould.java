package it.eparlato.socialnetworking.time;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TweakedApplicationClockShould {

    private final long ONE_HOUR = 60 * 60 * 1000;
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
    public void be_able_to_subtract_five_minutes_from_current_time() {
        long fiveMinutesAgo = now - (5 * ONE_MINUTE);
        applicationClock.subtractMinutes(5);

        assertEquals(fiveMinutesAgo, applicationClock.currentTimeMillis());
    }

    @Test
    public void be_able_to_subtract_fifteen_second_from_current_time() {
        long fifteenSecondsAgo = now - (15 * ONE_SECOND);
        applicationClock.subtractSeconds(15);

        assertEquals(fifteenSecondsAgo, applicationClock.currentTimeMillis());
    }

    @Test
    public void be_able_to_subtract_minutes_and_seconds_from_currente_time() {
        long fiftyHeightMinutesAndThirtySecondsAgo = now - (58 * ONE_MINUTE) - (30 * ONE_SECOND);
        applicationClock.subtractMinutes(58).subtractSeconds(30);

        assertEquals(fiftyHeightMinutesAndThirtySecondsAgo, applicationClock.currentTimeMillis());
    }

    @Test
    public void be_able_to_add_minutes_and_seconds_to_current_time() {
        long oneMinutesThirtySecondsAgo = now - (1 * ONE_MINUTE) - (30 * ONE_SECOND);

        applicationClock.subtractMinutes(3).addMinutes(1).addSeconds(30);

        assertEquals(oneMinutesThirtySecondsAgo, applicationClock.currentTimeMillis());
    }

    @Test
    public void be_able_to_override_existing_time() {
        applicationClock.subtractMinutes(5);

        applicationClock.setCurrentTimeMillis(now);

        assertEquals(now, applicationClock.currentTimeMillis());
    }
}
