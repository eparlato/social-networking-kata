package it.eparlato.socialnetworking.user.repository;

import it.eparlato.socialnetworking.user.User;

public class DummyUserRepository implements UserRepository {
    public User getUser(String userName) {
        return null;
    }

    @Override
    public User findUserInRepository(String username) {
        return null;
    }
}
