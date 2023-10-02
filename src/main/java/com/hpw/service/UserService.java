package com.hpw.service;

import com.hpw.domain.User;
import com.hpw.exception.ResourceNotFoundException;
import com.hpw.messages.ErrorMessages;
import com.hpw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;


    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND_MESSAGE, email)));
    }

}
