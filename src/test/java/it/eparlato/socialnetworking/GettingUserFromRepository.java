package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.user.InMemoryUser;
import it.eparlato.socialnetworking.user.User;
import it.eparlato.socialnetworking.user.repository.InMemoryUserRepository;
import it.eparlato.socialnetworking.user.repository.UserRepository;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GettingUserFromRepository {
    @Test
    public void a_user_repository_should_return_a_new_user_if_it_is_not_already_in_the_repository() {
        User userAlice = new InMemoryUser("Alice");
        UserRepository repository = new InMemoryUserRepository();

        assertEquals(null, repository.findUserInRepository("Alice"));
        assertEquals(userAlice, repository.getUser("Alice"));
    }

    @Test
    public void a_user_repository_should_return_an_existing_user() {
        UserRepository repository = new InMemoryUserRepository();

        User userAlice = repository.getUser("Alice");
        User userCharlie = repository.getUser("Charlie");

        assertEquals(userCharlie, repository.findUserInRepository("Charlie"));
        assertEquals(userAlice, repository.findUserInRepository("Alice"));
    }
}
