package com.hpw.contactmessage;

import com.hpw.contactmessage.payload.request.ContactMessageRequest;
import com.hpw.contactmessage.payload.response.ContactMessageResponse;
import com.hpw.exception.ConflictException;
import com.hpw.messages.ErrorMessages;
import com.hpw.messages.SuccessMessages;
import com.hpw.payload.response.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    //save()
    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {
        boolean isSameMessageOnTheSameDay = contactMessageRepository.existsByEmailEqualsAndDateEquals(contactMessageRequest.email(), LocalDate.now());
        if(isSameMessageOnTheSameDay){
            throw new ConflictException(ErrorMessages.ALREADY_SEND_A_MESSAGE_TODAY);
        }
        ContactMessage contactMessage=createContactMessage(contactMessageRequest);
        ContactMessage savedData=contactMessageRepository.save(contactMessage);
        return ResponseMessage.<ContactMessageResponse>builder()
                .message(SuccessMessages.CONTACT_MESSAGE_SAVED_SUCCESSFULLY)
                .httpStatus(HttpStatus.CREATED)
                .object(createResponse(savedData))
                .build();
    }

    //searchByEmail()
    public Page<ContactMessageResponse> searchByEmail(String email, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());

        if (Objects.equals(type, "desc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        }
        return contactMessageRepository.findByEmailEquals(email, pageable).map(this::createResponse);
    }


    //searchBySubject()
    public Page<ContactMessageResponse> searchBySubject(String subject, int page, int size, String sort, String type) {
        Pageable pageable = getPageableWithProperties(page, size, sort, type);
        return contactMessageRepository.findBySubjectEquals(subject, pageable).map(this::createResponse);

    }

    //pageable yapi icin yardimci method
    public Pageable getPageableWithProperties(int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        if (Objects.equals(type, "desc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        }
        return pageable;
    }

    // Pojo - DTO Donusumu icin yardimci method
    public ContactMessageResponse createResponse(ContactMessage contactMessage) {
        return new ContactMessageResponse(
                contactMessage.getName(),
                contactMessage.getEmail(),
                contactMessage.getSubject(),
                contactMessage.getMessage(),
                contactMessage.getDate());
    }
    private ContactMessage createContactMessage(ContactMessageRequest contactMessageRequest){
        return ContactMessage.builder()
                .name(contactMessageRequest.name())
                .subject(contactMessageRequest.subject())
                .message(contactMessageRequest.message())
                .email(contactMessageRequest.email())
                .date(LocalDate.now())
                .build();
    }



}
