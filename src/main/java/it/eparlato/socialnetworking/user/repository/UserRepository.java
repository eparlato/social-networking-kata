package it.eparlato.socialnetworking.user.repository;

import it.eparlato.socialnetworking.user.User;

public interface UserRepository {
    User getUser(String userName);

    // FIXME Interface Segregation Principle violation: this method has use only in tests defined in GettingUserFromRepository
    User findUserInRepository(String username);
}
