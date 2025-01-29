package az.edu.eshopwarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EshopWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopWarehouseApplication.class, args);
    }

}
