package com.hpw.contactmessage;

import com.hpw.contactmessage.payload.helper.PageableHelper;
import com.hpw.contactmessage.payload.mapper.DtoPojoMapper;
import com.hpw.contactmessage.payload.response.ContactMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final PageableHelper pageableHelper;
    private final DtoPojoMapper dtoPojoMapper;

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
}
