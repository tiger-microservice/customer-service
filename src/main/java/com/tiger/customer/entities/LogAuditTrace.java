package com.tiger.customer.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "log_audit_trace")
public class LogAuditTrace extends CreatedAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    UUID id;
    @Column(name = "table_name")
    String tableName;
    @Column(name = "action_modify")
    String actionModify;
    @Column(name = "old_value_json", columnDefinition = "TEXT")
    Object oldValueJson;
    @Column(name = "new_value_json", columnDefinition = "TEXT")
    Object newValueJson;
}
