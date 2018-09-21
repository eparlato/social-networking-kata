package it.eparlato.socialnetworking;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class ParsingWithRegex {

    private String regex = "(\\w*)(\\s(wall|->|follows)(\\s\\w*)*)*";

    @Test
    public void regex_should_match_the_username_alone() {
        assertTrue("Username doesn't match", matches("Alice"));
    }

    @Test
    public void regex_should_match_the_wall_command() {
        assertTrue("Wall command doesn't match", matches( "Bob wall"));
    }

    @Test
    public void regex_should_match_the_publish_command() {
        assertTrue("Publish command doesn't match", matches( "Ed -> Hi"));
    }

    @Test
    public void regex_should_match_the_follows_command() {
        assertTrue("Follows command doesn't match", matches("Bob follows Ed"));
    }

    private boolean matches(String input) {
        return Pattern.matches(regex, input);
    }
}
