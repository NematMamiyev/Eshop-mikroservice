package az.edu.eshopemployee.entity;

import az.edu.eshopemployee.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@DynamicInsert
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String surname;
    @Column(nullable = false, length = 70, unique = true)
    private String email;
    @Column(nullable = false, length = 30, unique = true)
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false, length = 200)
    private String password;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault(value = "1")
    private Integer active;
}
