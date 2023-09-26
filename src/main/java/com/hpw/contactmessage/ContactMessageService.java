package com.hpw.contactmessage;

import com.hpw.contactmessage.payload.request.ContactMessageRequest;
import com.hpw.contactmessage.payload.helper.PageableHelper;
import com.hpw.contactmessage.payload.mapper.DtoPojoMapper;
import com.hpw.contactmessage.payload.response.ContactMessageResponse;
import com.hpw.exception.ConflictException;
import com.hpw.exception.ResourceNotFoundException;
import com.hpw.messages.ErrorMessages;
import com.hpw.messages.SuccessMessages;
import com.hpw.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final PageableHelper pageableHelper;
    private final DtoPojoMapper dtoPojoMapper;


    //save()
    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {
        boolean isSameMessageOnTheSameDay = contactMessageRepository.existsByEmailEqualsAndDateEquals(contactMessageRequest.email(), LocalDate.now());
        if (isSameMessageOnTheSameDay) {
            throw new ConflictException(ErrorMessages.ALREADY_SEND_A_MESSAGE_TODAY);
        }
        ContactMessage contactMessage = createContactMessage(contactMessageRequest);
        ContactMessage savedData = contactMessageRepository.save(contactMessage);
        return ResponseMessage.<ContactMessageResponse>builder()
                .message(SuccessMessages.CONTACT_MESSAGE_SAVED_SUCCESSFULLY)
                .httpStatus(HttpStatus.CREATED)
                .object(dtoPojoMapper.createResponse(savedData))
                .build();
    }


    //searchByEmail()
    public Page<ContactMessageResponse> searchByEmail(String email, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        return contactMessageRepository.findByEmailEquals(email, pageable).map(dtoPojoMapper::createResponse);
    }


    //searchBySubject()
    public Page<ContactMessageResponse> searchBySubject(String subject, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        return contactMessageRepository.findBySubjectEquals(subject, pageable).map(dtoPojoMapper::createResponse);
    }

    private ContactMessage createContactMessage(ContactMessageRequest contactMessageRequest) {
        return ContactMessage.builder()
                .name(contactMessageRequest.name())
                .subject(contactMessageRequest.subject())
                .message(contactMessageRequest.message())
                .email(contactMessageRequest.email())
                .date(LocalDate.now())
                .build();
    }

    public List<ContactMessageResponse> getAllContactMessages() {

        return contactMessageRepository.findAll()
                .stream()
                .map(dtoPojoMapper::createResponse)
                .toList();
    }

    public ResponseMessage deleteContactMessage(Long contactMessageId) {

        isContactMessageExist(contactMessageId);
        contactMessageRepository.deleteById(contactMessageId);
        return ResponseMessage.builder()
                .message(SuccessMessages.CONTACT_MESSAGE_DELETED_SUCCESSFULLY)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private void isContactMessageExist(Long id) {

        contactMessageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.USER_MESSAGE_NOT_FOUND, id)));
    }
}
