package com.hpw.contactmessage.payload.helper;

import com.hpw.contactmessage.models.enums.SortingType;
import com.hpw.messages.ErrorMessages;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageableHelper {

    public Pageable getPageableWithProperties(final int page, final int size, final String sort, final String type) {
        // Sayfa ve boyut kontrolü
        if (page < 0 || size < 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_PAGE_OR_SIZE_VALUE);
        }
        // Tip kontrolü
        boolean isTypeOrderDescOrAsc =!type.equalsIgnoreCase(SortingType.ASC.getType()) && !type.equalsIgnoreCase(SortingType.DESC.getType());
        if (isTypeOrderDescOrAsc) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_TYPE_VALUE);
        }
        Sort sorting = type.equalsIgnoreCase(SortingType.DESC.getType()) ? Sort.by(sort).descending() : Sort.by(sort).ascending();

        return PageRequest.of(page, size, sorting);
    }

}
