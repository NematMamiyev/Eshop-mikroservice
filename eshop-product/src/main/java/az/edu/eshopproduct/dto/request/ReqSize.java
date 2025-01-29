package az.edu.eshopproduct.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReqSize {
    @NotBlank(message = "Name is required")
    private String name;
}
