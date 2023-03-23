package com.bank.transfer.service;

import com.bank.transfer.entity.Audit;
import com.bank.transfer.repository.AuditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    @Transactional
    public void save(Audit audit) {
        log.info("try to save audit: {}", audit);
        auditRepository.save(audit);
        log.info("save audit success, id={}", audit.getId());
    }

    @Override
    public Optional<Audit> getById(Long id) {
        return auditRepository.findById(id);
    }

    @Override
    public List<Audit> getAll() {
        return auditRepository.findAll();
    }

}
