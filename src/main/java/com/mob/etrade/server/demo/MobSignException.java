package com.mob.etrade.server.demo;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/21
 */
public class MobSignException extends MobException {
    private static final long serialVersionUID = -2367531026916399790L;

    public MobSignException() {
    }

    public MobSignException(String message) {
        super(message);
    }

    public MobSignException(String message, Throwable cause) {
        super(message, cause);
    }
}
