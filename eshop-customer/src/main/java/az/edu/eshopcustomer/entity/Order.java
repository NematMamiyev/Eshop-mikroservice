package az.edu.eshopcustomer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "product_order")
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "product_order_details",
            joinColumns = @JoinColumn(name = "product_order_id")
    )
    @MapKeyColumn(name = "product_details_id")
    @Column(name = "quantity")
    private Map<Long, Integer> productDetailsQuantityMap = new HashMap<>();

    @OneToMany(mappedBy = "order")
    private List<OrderStatus> orderStatusList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private BigDecimal amount;

    @CreationTimestamp
    private Date dataDate;

    @ColumnDefault(value = "1")
    private Integer active;
}
