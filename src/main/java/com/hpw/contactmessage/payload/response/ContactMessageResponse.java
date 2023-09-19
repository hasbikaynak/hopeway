package com.hpw.contactmessage.payload.response;

import java.time.LocalDate;

public record ContactMessageResponse(String name, String email, String subject, String message, LocalDate date)  {

}
