package com.hpw.contactmessage;

import org.springframework.stereotype.Service;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;


    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }


}
