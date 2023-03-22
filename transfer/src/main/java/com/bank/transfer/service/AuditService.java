package com.bank.transfer.service;

import com.bank.transfer.entity.Audit;

import java.util.List;
import java.util.Optional;

public interface AuditService {

    void save(Audit audit);

    Optional<Audit> getById(Long id);

    List<Audit> getAll();

}
