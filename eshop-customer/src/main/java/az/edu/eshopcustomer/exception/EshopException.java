package az.edu.eshopcustomer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EshopException extends RuntimeException{
        private Integer code;
        private String message;
}
