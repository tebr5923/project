package com.bank.profile.service.serviceInterface;

import com.bank.profile.entity.audit.Audit;

import java.util.List;

public interface AuditService {
    void saveAudit(Audit audit);

    Audit findAuditById(long auditId);

    void editAudit(Long id, Audit audit);

    void deleteAudit(long auditId);

    List<Audit> getAllAudit();
}
