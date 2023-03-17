package com.bank.transfer.service;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AccountTransferServiceImpl implements AccountTransferService {

    private final AccountTransferRepository accountTransferRepository;

    @Autowired
    public AccountTransferServiceImpl(AccountTransferRepository accountTransferRepository) {
        this.accountTransferRepository = accountTransferRepository;
    }

    @Override
    @Transactional
    public void save(AccountTransfer accountTransfer) {
        log.trace("try to save accountTransfer: {}", accountTransfer);
        accountTransferRepository.save(accountTransfer);
        log.trace("save accountTransfer success, id={}", accountTransfer.getId());
    }

    @Override
    @Transactional
    public void update(Long id, AccountTransfer accountTransfer) {
        accountTransfer.setId(id);

        log.trace("try to update accountTransfer: {}", accountTransfer);
        accountTransferRepository.save(accountTransfer);
        log.trace("update accountTransfer success, id={}", accountTransfer.getId());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.trace("try to delete accountTransfer with id={}", id);
        accountTransferRepository.deleteById(id);
        log.trace("success delete accountTransfer with id={}", id);
    }

    @Override
    public Optional<AccountTransfer> getById(Long id) {
        log.trace("try to get accountTransfer by id={}", id);
        var accountTransfer = accountTransferRepository.findById(id);
        log.trace("getById accountTransfer success, accountTransfer.isPresent() = {}", accountTransfer.isPresent());

        return accountTransfer;
    }

    @Override
    public Optional<AccountTransfer> getByAccountNumber(Long accountNumber) {
        log.trace("try to get accountTransfer by accountNumber={}", accountNumber);
        var accountTransfer = accountTransferRepository.getByAccountNumber(accountNumber);
        log.trace("getByAccountNumber accountTransfer success, accountTransfer.isPresent() = {}", accountTransfer.isPresent());

        return accountTransfer;
    }

    @Override
    public List<AccountTransfer> getAll() {
        log.trace("try to get All accountTransfers");
        var accountTransfers =accountTransferRepository.findAll();
        log.trace("getAll accountTransfers success, count = {}", accountTransfers.size());

        return accountTransfers;
    }
}
