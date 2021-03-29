package com.local.userwebapp.error;

// 「ユーザーが存在していない」エラー
/**
 * The Class UserNotFoundException.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Instantiates a new user not found exception.
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * Instantiates a new user not found exception.
     *
     * @param ex the ex
     */
    public UserNotFoundException(final Throwable ex) {
        super(ex);
    }
}
