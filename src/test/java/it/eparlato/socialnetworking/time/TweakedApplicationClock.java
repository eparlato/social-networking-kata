package it.eparlato.socialnetworking.time;

public class TweakedApplicationClock implements ApplicationClock {
    private final long ONE_HOUR = 60 * 60 * 1000;
    private final long ONE_SECOND = 1000;
    private final long ONE_MINUTE = 60 * 1000;

    private long now;

    public TweakedApplicationClock(long now) {
        this.now = now;
    }

    @Override
    public long now() {
        return now;
    }

    public TweakedApplicationClock subtractMinutes(int minutes) {
        this.now -= (minutes * ONE_MINUTE);
        return this;
    }

    public TweakedApplicationClock subtractSeconds(int seconds) {
        this.now -= (seconds * ONE_SECOND);
        return this;
    }

    public TweakedApplicationClock subtractHours(int hours) {
        this.now -= (hours * ONE_HOUR);
        return this;
    }
}
