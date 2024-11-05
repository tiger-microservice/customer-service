package vn.tiger.customer.entities;

import com.tiger.cores.entities.SoftDelEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "hospital")
@SQLDelete(sql = "UPDATE hospital set is_deleted = true where id = ?")
public class Hospital extends SoftDelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "hospital_id")
    UUID hospitalId;
    @Column(name = "name")
    String name;
    @Column(name = "contact_email")
    String contactEmail;
    @Column(name = "description")
    String description;
    @Column(name = "origin_hospital_id")
    String originHospitalId;
    @Column(name = "document_commitment")
    String documentCommitment;
    @Column(name = "effective_start_date")
    LocalDateTime effectiveStartDate;
    @Column(name = "effective_end_date")
    LocalDateTime effectiveEndDate;
}
