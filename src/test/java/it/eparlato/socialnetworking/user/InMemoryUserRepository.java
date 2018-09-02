package it.eparlato.socialnetworking.user;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    Map<String, User> repository = new HashMap<>();

    public User getUser(String username) {
        User user =  findUserInRepository(username);

        if (user == null) {
            user = new ConcreteUser(username);
            repository.put(username, user);
        }

        return user;
    }

    public User findUserInRepository(String username) {
        return repository.get(username);
    }
}
