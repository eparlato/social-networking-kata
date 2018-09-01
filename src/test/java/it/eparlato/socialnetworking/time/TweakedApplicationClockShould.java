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
    public void be_able_to_subtract_3_hours_from_current_time() {
        long threeHoursAgo = now - (3 * ONE_HOUR);
        applicationClock.subtractHours(3);

        assertEquals(threeHoursAgo, applicationClock.currentTimeMillis());
    }

    @Test
    public void be_able_to_subtract_hours_minutes_seconds_from_currente_time() {
        long oneHourFiftyNineMinutesAgo = now - ( (1 * ONE_HOUR) + (59 * ONE_MINUTE));
        applicationClock.subtractHours(1).subtractMinutes(59);

        assertEquals(oneHourFiftyNineMinutesAgo, applicationClock.currentTimeMillis());
    }

    @Test
    public void be_able_to_add_hours_minutes_second_to_current_time() {
        long oneMinutesThirtySecondsAgo = now - (1 * ONE_MINUTE) - (30 * ONE_SECOND);

        applicationClock.subtractHours(2).addHours(1).addMinutes(58).addSeconds(30);

        assertEquals(oneMinutesThirtySecondsAgo, applicationClock.currentTimeMillis());
    }
}
