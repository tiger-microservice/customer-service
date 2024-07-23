package com.tiger.customer.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "department")
@SQLDelete(sql = "UPDATE department set is_deleted = true where id = ?")
@SQLRestriction("is_deleted <> true")
public class Department extends SoftDelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "department_id")
    UUID departmentId;
    @Column(name = "name")
    String name;
    @Column(name = "phone_number")
    String phoneNumber;
    @Column(name = "hospital_id")
    UUID hospitalId;
}
