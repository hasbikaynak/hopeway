package com.hpw.messages;

public class ErrorMessages {
    private ErrorMessages() {
    }
    public static final String ALREADY_SEND_A_MESSAGE_TODAY = "Error : You have already send a message with this e-mail ";
    public static final String INVALID_PAGE_OR_SIZE_VALUE = "Invalid page or size value. Page and size values must be positive!";
    public static final String INVALID_TYPE_VALUE = "Invalid type value. Type value 'asc' or 'desc'!";
    public static final String INVALID_SORT_FIELD = "Invalid sort field. The sort field cannot be empty!";
    public static final String USER_MESSAGE_NOT_FOUND = "User not found with id %s";
    public static final String JWTTOKEN_ERROR_MESSAGE = "JWT Token Validation Error: %s";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found with email %s";
    public static final String USER_NOT_FOUND_WITH_USERNAME = "User not found with username %s";
}
