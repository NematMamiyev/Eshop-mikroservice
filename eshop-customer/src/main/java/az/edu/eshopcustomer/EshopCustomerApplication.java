package az.edu.eshopcustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@EnableFeignClients
public class EshopCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopCustomerApplication.class, args);
    }

}
