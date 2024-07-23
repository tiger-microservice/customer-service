package com.tiger.customer.listeners;


import com.tiger.common.utils.ObjectMapperUtil;
import com.tiger.customer.constants.enums.ActionModified;
import com.tiger.customer.entities.LogAuditTrace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class LogAuditTraceListener {

    LogAuditTrace logAuditTrace;

    @PrePersist
    // before
    public void prePersist(Object entity) {
        log.info("prePersist");
    }

    @PostPersist
    // after insert
    public void postPersist(Object entity) {
        log.info("postPersist");
    }

    // update
    @PreUpdate
    public void preUpdate(Object entity) {
        log.info("preUpdate");
        saveLogAuditTrace(entity, ActionModified.UPDATE);
    }

    @PostUpdate
    public void postUpdate(Object entity) {
        log.info("preUpdate");
        saveLogAuditTrace(entity, ActionModified.UPDATE);
    }

    // delete
    @PostRemove
    public void postRemove(Object entity) {
        log.info("postRemove");
        saveLogAuditTrace(entity, ActionModified.DELETE);
    }

    private void saveLogAuditTrace(Object entity, ActionModified action) {
        LogAuditTrace logAuditTrace = LogAuditTrace.builder()
                .actionModify(action.toString())
                .newValueJson(ObjectMapperUtil.castToString(entity))
                .build();

    }
}
