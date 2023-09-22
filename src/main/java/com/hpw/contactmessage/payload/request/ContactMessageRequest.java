package com.hpw.contactmessage.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactMessageRequest (@NotNull(message = "Please enter name")
                                     @Size(min=2,max = 16, message = "Your name should be at least 2 characters")
                                     @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Your message must consist of the character .")
                                     String name,
                                     @Email(message = "Please enter valid email")
                                     @Size(min=5,max = 30, message = "Your email should be at least 5 characters")
                                     @NotNull(message = "Please enter your email")
                                     String email,
                                     @Size(min=4,max = 50, message = "Your subject should be at least 4 characters")
                                     @NotNull(message = "Please enter subject")
                                     @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Your subject must consist of the character .")
                                     String subject,
                                     @Size(min=4,max = 300, message = "Your message should be at least 4 characters")
                                     @NotNull(message = "Please enter message")
                                     @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Your message must consist of the character .")
                                     String message){
}
