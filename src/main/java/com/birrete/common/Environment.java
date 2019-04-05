package com.birrete.common;

import java.util.Optional;

public class Environment {
    public static String getClientID() {
        Optional<String> clientId = Optional.ofNullable(System.getenv("CLIENT_ID"));
        return clientId.orElseThrow(IllegalStateException::new);
    }
}
