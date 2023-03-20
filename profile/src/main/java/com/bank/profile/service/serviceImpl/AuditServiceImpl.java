package com.bank.profile.service.serviceImpl;

import com.bank.profile.entity.audit.Audit;
import com.bank.profile.repository.AuditRepository;
import com.bank.profile.service.serviceInterface.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public void saveAudit(Audit audit) {
        auditRepository.save(audit);
    }

    @Override
    public Audit findAuditById(long auditId) {
        return auditRepository.findById(auditId).orElseThrow(NullPointerException::new);
    }

    @Override
    public void editAudit(Long id, Audit audit) {
        audit.setId(id);
        auditRepository.save(audit);
    }

    @Override
    public void deleteAudit(long auditId) {
        auditRepository.deleteById(auditId);
    }

    @Override
    public List<Audit> getAllAudit() {
        return auditRepository.findAll();
    }
}
