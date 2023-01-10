package com.xpand.challenge.exception;

public class ActorServiceException extends RuntimeException {


    public ActorServiceException(final String message) {
        super(message);
    }

    public static ActorServiceException actorNotFound() {
        return new ActorServiceException("NOT FOUND");
    }
}
