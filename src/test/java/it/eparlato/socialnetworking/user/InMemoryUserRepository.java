package it.eparlato.socialnetworking.user;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    public User getUser(String userName) {
        return new ConcreteUser(userName);
    }
}
