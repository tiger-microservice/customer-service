package vn.tiger.customer.entities;

import com.tiger.cores.entities.SoftDelEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customer")
@SQLDelete(sql = "UPDATE customer set is_deleted = true where id = ?")
@SQLRestriction("is_deleted <> true")
public class Customer extends SoftDelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    UUID customerId;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "phone_number")
    String phoneNumber;
    @Column(name = "email")
    String email;
    @Column(name = "address")
    String address;
    @Column(name = "dob")
    LocalDate dob;
    @Column(name = "department_id")
    UUID departmentId;
}
