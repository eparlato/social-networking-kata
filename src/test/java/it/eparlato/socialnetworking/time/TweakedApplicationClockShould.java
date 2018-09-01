package it.eparlato.socialnetworking.time;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TweakedApplicationClockShould {

    private final long ONE_HOUR = 60 * 60 * 1000;
    private final long ONE_MINUTE = 60 * 1000;
    private final long ONE_SECOND = 1000;

    long now;
    TweakedApplicationClock timeManager;

    @Before
    public void setup() {
        now = System.currentTimeMillis();
        timeManager = new TweakedApplicationClock(now);
    }

    @Test
    public void be_able_to_subtract_five_minutes_from_current_time() {
        long fiveMinutesAgo = now - (5 * ONE_MINUTE);
        timeManager.subtractMinutes(5);

        assertEquals(fiveMinutesAgo, timeManager.now());
    }

    @Test
    public void be_able_to_subtract_fifteen_second_from_current_time() {
        long fifteenSecondsAgo = now - (15 * ONE_SECOND);
        timeManager.subtractSeconds(15);

        assertEquals(fifteenSecondsAgo, timeManager.now());
    }

    @Test
    public void be_able_to_subtract_3_hours_from_current_time() {
        long threeHoursAgo = now - (3 * ONE_HOUR);
        timeManager.subtractHours(3);

        assertEquals(threeHoursAgo, timeManager.now());
    }

    @Test
    public void be_able_to_subtract_huours_minutes_seconds_from_currente_time() {
        long oneHourFiftyNineMinutesAgo = now - ( (1 * ONE_HOUR) + (59 * ONE_MINUTE));
        timeManager.subtractHours(1).subtractMinutes(59);

        assertEquals(oneHourFiftyNineMinutesAgo, timeManager.now());
    }


}
