package it.eparlato.socialnetworking;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class ParsingWithRegex {

    private String regex = "(\\w*)((\\s(wall))*)";

    @Test
    public void regex_should_match_the_username_alone() {
        assertTrue("Username doesn't match", Pattern.matches(regex, "Alice"));
    }

    @Test
    public void regex_should_match_the_wall_command() {
        assertTrue("Wall command doesn't match", Pattern.matches(regex, "Bob wall"));
    }
}