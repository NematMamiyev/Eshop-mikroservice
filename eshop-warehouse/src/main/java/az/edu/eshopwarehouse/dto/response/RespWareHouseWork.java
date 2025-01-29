package az.edu.eshopwarehouse.dto.response;

import az.edu.eshopwarehouse.client.customer.RespOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespWareHouseWork {
    private Long id;
    private RespOrder respOrder;
    private Date dataDate;
}
