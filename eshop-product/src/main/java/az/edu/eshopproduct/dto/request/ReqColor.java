package az.edu.eshopproduct.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReqColor {
    @NotBlank(message = "Name is required")
    @Size(min = 2,max = 70,message = "Name must be between 2 and 70 characters")
    private String name;
}
