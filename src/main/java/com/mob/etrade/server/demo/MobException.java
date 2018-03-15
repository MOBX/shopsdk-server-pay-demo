package com.mob.etrade.server.demo;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/23
 */
public class MobException extends RuntimeException {

    private static final long serialVersionUID = -6442335677947941282L;

    public MobException() {
    }

    public MobException(String message) {
        super(message);
    }

    public MobException(String message, Throwable cause) {
        super(message, cause);
    }
}
