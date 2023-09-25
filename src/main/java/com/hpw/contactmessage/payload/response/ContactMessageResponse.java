package com.hpw.contactmessage.payload.response;

import java.time.LocalDate;

public record ContactMessageResponse(Long id, String name, String email, String subject, String message, LocalDate date)  {

}
