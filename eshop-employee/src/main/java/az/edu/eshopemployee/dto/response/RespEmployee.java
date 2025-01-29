package az.edu.eshopemployee.dto.response;

import az.edu.eshopemployee.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespEmployee {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Role role;
}
