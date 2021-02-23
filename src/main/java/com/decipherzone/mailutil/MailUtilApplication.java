package com.decipherzone.mailutil;

import com.decipherzone.mailutil.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.decipherzone")
@RestController
public class MailUtilApplication {

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(MailUtilApplication.class, args);
    }
    @GetMapping("/sendMail")
    public ResponseEntity sendMail(){
        List<String> mails= new ArrayList<>();
        mails.add("purushottam@decipherzone.com");
     if( emailSenderService.sendSimpleMessage(mails,"test","you had done")){
         return new ResponseEntity(HttpStatus.OK);
     }
        return new ResponseEntity(HttpStatus.valueOf(500));
    }
}
