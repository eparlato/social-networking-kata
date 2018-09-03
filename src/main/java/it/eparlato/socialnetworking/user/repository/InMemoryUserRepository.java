package it.eparlato.socialnetworking.user.repository;

import it.eparlato.socialnetworking.user.ConcreteUser;
import it.eparlato.socialnetworking.user.User;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    Map<String, User> repository = new HashMap<String, User>();

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
