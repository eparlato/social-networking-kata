package it.eparlato.socialnetworking.user;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserRepositoryShould {
    @Test
    public void return_a_new_user_if_it_is_not_already_in_the_repository() {
        User userAlice = new ConcreteUser("Alice");
        UserRepository repository = new InMemoryUserRepository();

        assertEquals(userAlice, repository.getUser("Alice"));
    }

    // get a user that is already in the repository (create a user with some messages published)
}
