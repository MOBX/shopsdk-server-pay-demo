package com.mob.etrade.server.demo;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/23
 */
public class MobApiException extends MobException {
    private static final long serialVersionUID = -5911186399589781736L;

    public MobApiException() {
    }

    public MobApiException(String message) {
        super(message);
    }

    public MobApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
