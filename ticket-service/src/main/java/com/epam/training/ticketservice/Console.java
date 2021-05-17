package com.epam.training.ticketservice;

import org.springframework.stereotype.Service;
import java.io.PrintStream;

@Service
public class Console {
    private final PrintStream out = System.out;

    public void print(String text, String... args) {
        this.out.printf(text, (Object[]) args);
        this.out.println();
    }

    public void printErr(String text, String... args) {
        this.out.printf(text, (Object[]) args);
        this.out.println();
    }
}
