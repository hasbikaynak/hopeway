package com.hpw.contactmessage;


import com.hpw.contactmessage.payload.response.ContactMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactMessages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;


    @PostMapping("/save")   // save()  http://localhost:8080/contactMessages/save +POST
    // @PreAuthorize("hasAnyAuthority('ADMIN', USER, USER_GUEST)")
    public ResponseMessage<ContactMessageResponse> save(@RequestBody @Valid ContactMessageRequest contactMessageRequest){
        return contactMessageService.save(contactMessageRequest);
    }

    /*
    {
        "content": [
        {
            "name":"aaaa",
                "email":"beyza@gmail.com",
                "subject":"aaa",
                "message":"aa",
                "date":null
        }
   ],
        "pageable":{
        "pageNumber":0,
                "pageSize":10,
                "sort":{
            "empty":false,
                    "sorted":true,
                    "unsorted":false
        },
        "offset":0,
                "paged":true,
                "unpaged":false
    },
        "last":true,
            "totalPages":1,
            "totalElements":1,
            "size":10,
            "number":0,
            "sort":{
        "empty":false,
                "sorted":true,
                "unsorted":false
    },
        "first":true,
            "numberOfElements":1,
            "empty":false
    }*/

    // http://localhost:8080/contactMessages/searchByEmail?email=beyza@gmail.com&page=0 + GET
    //searchByEmail()
    @GetMapping("/searchByEmail")
    // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<ContactMessageResponse> searchByEmail(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "date") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {
        return contactMessageService.searchByEmail(email, page, size, sort, type);
    }

    // http://localhost:8080/contactMessages/searchBySubject?subject=deneme + GET
    //searchBySubject()
    @GetMapping("/searchBySubject")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<ContactMessageResponse> searchBySubject(
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "date") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ) {
        return contactMessageService.searchBySubject(subject, page, size, sort, type);

    }
}
