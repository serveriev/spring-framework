package io.lenur.conditional.service.impl;

import io.lenur.conditional.service.Message;

public class GreetingMessageImpl implements Message {
    @Override
    public String getMessage() {
        return "Greeting";
    }
}
