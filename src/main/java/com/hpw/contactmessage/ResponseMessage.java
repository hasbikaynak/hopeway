package com.hpw.contactmessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
// null olmaqyan fieldlar JSON dosyaya dahil edilecek-> sadece null olmayanlar
@Builder(toBuilder = true)
public record ResponseMessage<E>(E object, String message, HttpStatus httpStatus) {
}
