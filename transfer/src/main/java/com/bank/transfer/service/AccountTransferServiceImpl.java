package com.bank.transfer.service;

import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AccountTransferServiceImpl implements AccountTransferService{

    private final AccountTransferRepository accountTransferRepository;

    @Autowired
    public AccountTransferServiceImpl(AccountTransferRepository accountTransferRepository) {
        this.accountTransferRepository = accountTransferRepository;
    }

    @Override
    @Transactional
    public void save(AccountTransfer accountTransfer) {
        accountTransferRepository.save(accountTransfer);
    }

    @Override
    @Transactional
    public void update(Long id, AccountTransfer accountTransfer) {
        accountTransfer.setId(id);
        accountTransferRepository.save(accountTransfer);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        accountTransferRepository.deleteById(id);
    }

    @Override
    public Optional<AccountTransfer> getById(Long id) {
        return accountTransferRepository.findById(id);
    }

    @Override
    public Optional<AccountTransfer> getByAccountNumber(Long accountNumber) {
        return accountTransferRepository.getByAccountNumber(accountNumber);
    }

    @Override
    public List<AccountTransfer> getAll() {
        return accountTransferRepository.findAll();
    }
}
