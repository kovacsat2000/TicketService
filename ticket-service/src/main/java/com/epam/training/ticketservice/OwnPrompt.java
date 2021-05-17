package com.epam.training.ticketservice;

import org.springframework.stereotype.Component;
import org.springframework.shell.jline.PromptProvider;
import org.jline.utils.AttributedString;

@Component
public class OwnPrompt implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("Ticket service>");
    }

}
