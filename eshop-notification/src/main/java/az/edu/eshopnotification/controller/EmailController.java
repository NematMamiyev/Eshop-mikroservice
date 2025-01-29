package az.edu.eshopnotification.controller;

import az.edu.eshopnotification.model.EmailDto;
import az.edu.eshopnotification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendmail")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public String sendEmail(@RequestBody EmailDto emailDto){
        emailService.sendEmail(emailDto.getTo(),emailDto.getSubject(),emailDto.getBody());
        return "OK";
    }
}
