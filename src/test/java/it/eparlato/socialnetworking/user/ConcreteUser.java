package it.eparlato.socialnetworking.user;

public class ConcreteUser implements User {
    private final String userName;

    public ConcreteUser(String userName) {
        this.userName = userName;
    }

    public void post(String message) {
        System.out.println(String.format("User %s posted the message: %s", userName, message));
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ConcreteUser)) {
            return false;
        }

        ConcreteUser that = (ConcreteUser) obj;

        return this.userName.equals(that.userName);

    }
}
