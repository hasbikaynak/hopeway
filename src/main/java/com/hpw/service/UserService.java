package com.hpw.service;

import com.hpw.domain.User;
import com.hpw.exception.ResourceNotFoundException;
import com.hpw.messages.ErrorMessages;
import com.hpw.payload.response.UserResponse;
import com.hpw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;


    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND_MESSAGE, email)));
    }
    public List<UserResponse> getUserByUsername(String username) {

        return userRepository.getUserByUsernameContaining(username)
                .stream()
                .map(this::createUserResponse)
                .collect(Collectors.toList());
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
                // TODO user domaine eklemeler yapildiginda burayi duzelt
                .build();
    }

    public List<UserResponse> getUsersByEmail(String email) {
        return userRepository.getUserByEmailContaining(email)
                .stream()
                .map(this::createUserResponse)
                .collect(Collectors.toList());
    }
}
