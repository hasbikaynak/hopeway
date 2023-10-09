package com.hpw.service;

import com.hpw.domain.User;
import com.hpw.exception.ResourceNotFoundException;
import com.hpw.messages.ErrorMessages;
import com.hpw.payload.mappers.UserMapper;
import com.hpw.payload.response.UserResponse;
import com.hpw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private UserMapper userMapper;


    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND_MESSAGE, email)));
    }

    //getById()
    public UserResponse getUserById(Long id) {
        //id kontrolü
        User user = isUserExist(id);
        return userMapper.mapUserToUserResponse(user);

    }
    //yardimci method
    private User isUserExist(Long id){
        return userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages.USER_MESSAGE_NOT_FOUND, id)));
    }
}
