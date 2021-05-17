package com.epam.training.ticketservice.user;

public interface UserService {
    boolean isSignedIn();

    void signIn(String username, String password);

    void signOut();

    void describe();
}
