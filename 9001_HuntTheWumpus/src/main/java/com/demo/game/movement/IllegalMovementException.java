package com.demo.game.movement;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/2/26
 */
public class IllegalMovementException extends RuntimeException {
    public IllegalMovementException() {
    }

    public IllegalMovementException(String message) {
        super(message);
    }

    public IllegalMovementException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalMovementException(Throwable cause) {
        super(cause);
    }

    public IllegalMovementException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
