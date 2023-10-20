package com.hpw.payload.mappers;

import com.hpw.domain.User;
import com.hpw.payload.response.UserResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse mapUserToUserResponse(User user) {
        return null;
    }
}
