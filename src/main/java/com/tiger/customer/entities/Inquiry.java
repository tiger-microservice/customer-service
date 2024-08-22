package com.tiger.customer.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import com.tiger.cores.entities.SoftDelEntity;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "inquiry")
@SQLDelete(sql = "UPDATE inquiry set is_deleted = true where id = ?")
@SQLRestriction("is_deleted <> true")
public class Inquiry extends SoftDelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "inquiry_id")
    UUID id;
    @Column(name = "description")
    String description;
    @Column(name = "hospital_id")
    UUID hospitalId;
}
