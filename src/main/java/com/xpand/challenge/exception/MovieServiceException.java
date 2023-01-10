package com.xpand.challenge.exception;

public class MovieServiceException extends RuntimeException {

    public MovieServiceException(String message) {
        super(message);
    }

    public static MovieServiceException movieNotFound() {
        return new MovieServiceException("NOT FOUND");
    }
}
