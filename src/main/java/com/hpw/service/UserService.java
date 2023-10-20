package com.hpw.service;

import com.hpw.domain.User;
import com.hpw.exception.ResourceNotFoundException;
import com.hpw.messages.ErrorMessages;
import com.hpw.messages.SuccessMessages;
import com.hpw.payload.mappers.UserMapper;
import com.hpw.payload.response.ResponseMessage;
import com.hpw.payload.response.UserResponse;
import com.hpw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private UserMapper userMapper;


    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND_MESSAGE, email)));
    }
    public UserResponse getUserByUsername(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND_WITH_USERNAME, userName)));
        return createUserResponse(user);
    }

    private UserResponse createUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .zipCode(user.getZipCode())
                .userName(user.getUserName())
                .nationality(user.getNationality())
                .asylumCountry(user.getAsylumCountry())
                .registrationDate(user.getRegistrationDate())
                .built_in(user.isBuilt_in())
                .build();
    }

    public UserResponse getUsersByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND_MESSAGE, email)));
        return createUserResponse(user);

    }
    public UserResponse getUserById(Long id) {
        User user = isUserExist(id);
        return userMapper.mapUserToUserResponse(user);
    }

    private User isUserExist(Long id){
        return userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages.USER_MESSAGE_NOT_FOUND, id)));
    }

    public ResponseMessage deleteUser(Long id) {
        isUserExist(id);
        userRepository.deleteById(id);
        return ResponseMessage.builder()
                .message(SuccessMessages.USER_DELETED_SUCCESSFULLY)
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
