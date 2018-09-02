package it.eparlato.socialnetworking.user;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserRepositoryShould {
    @Test
    public void return_a_new_user_if_it_is_not_already_in_the_repository() {
        User userAlice = new ConcreteUser("Alice");
        UserRepository repository = new InMemoryUserRepository();

        assertEquals(null, repository.findUserInRepository("Alice"));
        assertEquals(userAlice, repository.getUser("Alice"));
    }

    @Test
    public void return_an_existing_user() {
        UserRepository repository = new InMemoryUserRepository();

        User userAlice = repository.getUser("Alice");
        User userCharlie = repository.getUser("Charlie");

        assertEquals(userCharlie, repository.findUserInRepository("Charlie"));
        assertEquals(userAlice, repository.findUserInRepository("Alice"));
    }
}
