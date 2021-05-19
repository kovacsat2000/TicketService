package com.epam.training.ticketservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import com.epam.training.ticketservice.Console;

@Service
public class UserServiceImpl implements UserService {
    private final Console console;

    private final AtomicBoolean signedIn = new AtomicBoolean();

    @Autowired
    public UserServiceImpl(Console console) {
        this.console = console;
    }

    @Override
    public boolean isSignedIn() {
        return this.signedIn.get();
    }

    @Override
    public void signIn(String username, String password) {

        String adminUsername = "admin";
        String adminPassword = "admin";
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            this.signedIn.set(true);
        }

        if (this.signedIn.get()) {
            console.print("Signed in with privileged account '%s'", adminUsername);
        }

        if (!this.signedIn.get()) {
            console.printErr("Login failed due to incorrect credentials");
        }
    }

    @Override
    public void signOut() {
        this.signedIn.set(false);
    }

    @Override
    public void describe() {

        if (this.signedIn.get()) {
            console.print("Signed in with privileged account '%s'", "admin");
        } else {
            console.printErr("You are not signed in");
        }

    }
}
