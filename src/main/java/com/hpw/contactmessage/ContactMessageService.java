package com.hpw.contactmessage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public Page<ContactMessageResponse> searchByEmail(String email, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());

        if (Objects.equals(type,"desc")){
            pageable = PageRequest.of(page, size,Sort.by(sort).descending());
        }

        return contactMessageRepository.findByEmailEquals(email,pageable).map(this::createResponse);

    }

    // Pojo - DTO Donusumu icin yardimci method
    public ContactMessageResponse createResponse(ContactMessage contactMessage) {
        return new ContactMessageResponse(  contactMessage.getName(),
                                            contactMessage.getEmail(),
                                            contactMessage.getSubject(),
                                            contactMessage.getMessage(),
                                            contactMessage.getDate());
    }

}
