package it.eparlato.socialnetworking.time;

public class TweakedApplicationClock implements ApplicationClock {
    private final long ONE_HOUR = 60 * 60 * 1000;
    private final long ONE_SECOND = 1000;
    private final long ONE_MINUTE = 60 * 1000;

    private long currentTimeMillis;

    public TweakedApplicationClock(long now) {
        this.currentTimeMillis = now;
    }

    @Override
    public long currentTimeMillis() {
        return currentTimeMillis;
    }

    public TweakedApplicationClock subtractMinutes(int minutes) {
        this.currentTimeMillis -= (minutes * ONE_MINUTE);
        return this;
    }

    public TweakedApplicationClock subtractSeconds(int seconds) {
        this.currentTimeMillis -= (seconds * ONE_SECOND);
        return this;
    }

    public TweakedApplicationClock subtractHours(int hours) {
        this.currentTimeMillis -= (hours * ONE_HOUR);
        return this;
    }

    public TweakedApplicationClock addMinutes(int minutes) {
        this.currentTimeMillis += (minutes * ONE_MINUTE);
        return this;
    }

    public TweakedApplicationClock addHours(int hours) {
        this.currentTimeMillis += (hours * ONE_HOUR);
        return this;
    }

    public TweakedApplicationClock addSeconds(int seconds) {
        this.currentTimeMillis += (seconds * ONE_SECOND);
        return this;
    }
}