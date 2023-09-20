package com.hpw.contactmessage;


import com.hpw.contactmessage.payload.response.ContactMessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactMessages")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }



    //searchByEmail()
    @GetMapping("/searchByEmail")
   // @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<ContactMessageResponse> searchByEmail( // http://localhost:8080/contactMessages/searchByEmail?email=beyza@gmail.com&page=0
                                                       @RequestParam(value = "email") String email,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                                       @RequestParam(value = "sort", defaultValue = "date") String sort,
                                                       @RequestParam(value = "type", defaultValue = "desc") String type) {
        return contactMessageService.searchByEmail(email, page, size, sort, type);
    }

    //searchBySubject()
    @GetMapping("/searchBySubject")  //// http://localhost:8080/contactMessages/searchBySubject?subject=deneme + GET
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<ContactMessageResponse> searchBySubject(
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "date") String sort,
            @RequestParam(value = "type",defaultValue = "desc") String type
    ){
        return contactMessageService.searchBySubject(subject,page,size,sort,type);


    }
}
