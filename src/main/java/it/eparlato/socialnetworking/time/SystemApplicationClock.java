package it.eparlato.socialnetworking.time;

public class SystemApplicationClock implements ApplicationClock {
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
