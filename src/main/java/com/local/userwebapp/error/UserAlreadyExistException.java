package com.local.userwebapp.error;

// 「ユーザーがすでに存在しています」エラー
/**
 * The Class UserAlreadyExistException.
 */
public class UserAlreadyExistException extends RuntimeException {

    /**
     * Instantiates a new user already exist exception.
     */
    public UserAlreadyExistException() {
        super();
    }
    
    /**
     * Instantiates a new user already exist exception.
     *
     * @param ex the ex
     */
    public UserAlreadyExistException(final Throwable ex) {
        super(ex);
    }
}
