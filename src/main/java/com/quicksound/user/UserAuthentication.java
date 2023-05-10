package com.quicksound.user;

public enum UserAuthentication {
    INSTANCE;
    public boolean authenticate(User user, String username, String password) {
        return user.getName().equals(username) && user.getPassword().equals(password);
    }


}