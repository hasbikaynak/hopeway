package com.hpw.contactmessage;


import com.hpw.contactmessage.payload.request.ContactMessageRequest;
import com.hpw.contactmessage.payload.response.ContactMessageResponse;
import com.hpw.payload.response.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contactMessages")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }




    @PostMapping("/save")   //save() http://localhost:8080/contactMessages/save + POST
    // @PreAuthorize("hasAnyAuthority('ADMIN', USER, USER_GUEST)")
    public ResponseMessage<ContactMessageResponse> save(@RequestBody @Valid ContactMessageRequest contactMessageRequest){
        return contactMessageService.save(contactMessageRequest);
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
