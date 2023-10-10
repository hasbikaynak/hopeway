package com.hpw.utils;

import com.hpw.exception.ConflictException;
import com.hpw.messages.ErrorMessages;
import com.hpw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceHelpers {

    private final UserRepository userRepository;

    public void checkDuplicate(String email, String phoneNumber){

        if (userRepository.existsByEmail(email) || userRepository.existsByPhoneNumber(phoneNumber)){
            throw  new ConflictException(String.format(ErrorMessages.USER_ALREADY_REGISTER_MESSAGE, email, phoneNumber));
        }
    }
}
