package com.bank.transfer.service;

import com.bank.transfer.aop.DeleteToLog;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.repository.PhoneTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional(readOnly = true)
public class PhoneTransferServiceImpl implements TransferService<PhoneTransfer> {

    private final PhoneTransferRepository repository;

    @Autowired
    public PhoneTransferServiceImpl(PhoneTransferRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void save(PhoneTransfer transfer) {
        log.info("try to save phoneTransfer: {}", transfer);
        repository.save(transfer);
        log.info("save phoneTransfer success, id={}", transfer.getId());
    }

    @Override
    @Transactional
    public void update(Long id, PhoneTransfer transfer) {
        transfer.setId(id);

        log.info("try to update phoneTransfer: {}", transfer);
        repository.save(transfer);
        log.info("update phoneTransfer success, id={}", transfer.getId());
    }

    @Override
    @Transactional
    @DeleteToLog
    public void delete(Long id) {
        log.info("try to delete phoneTransfer with id={}", id);
        repository.deleteById(id);
        log.info("success delete phoneTransfer with id={}", id);
    }

    @Override
    public Optional<PhoneTransfer> getById(Long id) {
        log.info("try to get phoneTransfer by id={}", id);
        var transfer = repository.findById(id);
        log.info("getById phoneTransfer success, transfer.isPresent() = {}", transfer.isPresent());

        return transfer;
    }

    @Override
    public Optional<PhoneTransfer> getByNumber(Long number) {
        log.info("try to get phoneTransfer by cardNumber={}", number);
        var transfer = repository.getByPhoneNumber(number);
        log.info("getByNumber phoneTransfer success, transfer.isPresent() = {}", transfer.isPresent());

        return transfer;
    }

    @Override
    public List<PhoneTransfer> getAll() {
        log.info("try to get All phoneTransfers");
        var transfers =repository.findAll();
        log.info("getAll phoneTransfers success, count = {}", transfers.size());

        return transfers;
    }

}
