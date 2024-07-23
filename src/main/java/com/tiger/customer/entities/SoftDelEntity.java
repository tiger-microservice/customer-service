package com.tiger.customer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class SoftDelEntity extends AuditEntity {

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    @Builder.Default
    private Boolean isDeleted = false;
}
