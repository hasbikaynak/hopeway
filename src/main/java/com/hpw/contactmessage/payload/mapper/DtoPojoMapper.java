package com.hpw.contactmessage.payload.mapper;

import com.hpw.contactmessage.ContactMessage;
import com.hpw.contactmessage.payload.response.ContactMessageResponse;
import org.springframework.stereotype.Component;

@Component
public class DtoPojoMapper {

    // Pojo - DTO Donusumu icin yardimci method
    public ContactMessageResponse createResponse(ContactMessage contactMessage) {
        return new ContactMessageResponse(
                contactMessage.getName(),
                contactMessage.getEmail(),
                contactMessage.getSubject(),
                contactMessage.getMessage(),
                contactMessage.getDate());
    }
}
