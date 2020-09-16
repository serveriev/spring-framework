package io.lenur.conditional.service.impl;

import io.lenur.conditional.service.Message;

public class HelloMessageImpl implements Message {
    @Override
    public String getMessage() {
        return "Hello";
    }
}
