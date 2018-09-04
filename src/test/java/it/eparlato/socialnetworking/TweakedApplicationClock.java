package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.time.ApplicationClock;

public class TweakedApplicationClock implements ApplicationClock {
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

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }

    public TweakedApplicationClock subtractMinutes(int minutes) {
        this.currentTimeMillis -= (minutes * ONE_MINUTE);
        return this;
    }

    public TweakedApplicationClock subtractSeconds(int seconds) {
        this.currentTimeMillis -= (seconds * ONE_SECOND);
        return this;
    }

    public TweakedApplicationClock addMinutes(int minutes) {
        this.currentTimeMillis += (minutes * ONE_MINUTE);
        return this;
    }

    public TweakedApplicationClock addSeconds(int seconds) {
        this.currentTimeMillis += (seconds * ONE_SECOND);
        return this;
    }
}
