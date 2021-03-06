package it.eparlato.socialnetworking.user.repository;

import it.eparlato.socialnetworking.user.User;

public interface UserRepository {
    User getUser(String userName);

    User findUserInRepository(String username);
}
