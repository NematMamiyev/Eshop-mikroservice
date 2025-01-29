package az.edu.eshopcustomer.client.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "eshop-notification")
public interface NotificationClient {

    @PostMapping("sendmail")
    String sendEmail(@RequestBody EmailDto emailDto);
}
