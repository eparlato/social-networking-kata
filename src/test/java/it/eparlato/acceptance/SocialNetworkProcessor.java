package it.eparlato.acceptance;

public class SocialNetworkProcessor {
    public String process(String command) {
        if ("Alice".equals(command)) {
            return "User Alice doesn't exist";
        }

        return "";
    }
}
