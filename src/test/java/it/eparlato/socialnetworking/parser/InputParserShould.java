package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.*;
import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import it.eparlato.socialnetworking.user.ConcreteUser;
import it.eparlato.socialnetworking.user.repository.InMemoryUserRepository;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class InputParserShould {
    Command expectedCommand;
    InputParser parser;
    String input;
    long now;

    @Before
    public void setup() {
        now = System.currentTimeMillis();
        parser = new ConcreteInputParser(new InMemoryUserRepository(), new TweakedApplicationClock(now));
    }

    @Test
    public void return_a_Publish_command_if_the_input_is_a_username_followed_by_a_message() {
        input = "Bob -> Damn! We lost!";
        expectedCommand = new Publish(new ConcreteUser("Bob"), "Damn! We lost!", now);

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void return_a_ViewTimeline_command_if_the_is_input_a_user_name() {
        input = "Alice";
        expectedCommand = new ViewTimeline(new ConcreteUser("Alice"), now);

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void return_a_Follow_command_if_the_input_contains_the_string_follows() {
        input = "Alice follows Charlie";
        expectedCommand = new Follow(new ConcreteUser("Alice"), new ConcreteUser("Charlie"));

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void return_a_Wall_command_if_the_input_contains_the_string_wall() {
        input = "Bob wall";
        expectedCommand = new Wall(new ConcreteUser("Bob"), now);

        assertEquals(expectedCommand, parser.parse(input));
    }
}
