package com.douglasmatosdev.builders;

import com.douglasmatosdev.entities.User;

public class UserBuilder {

    private User user;

    private UserBuilder() {
    }

    public static UserBuilder oneUser() {
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.user = new User();
        userBuilder.user.setName("User 1");
        return userBuilder;
    }

    public User now() {
        return user;
    }
}

