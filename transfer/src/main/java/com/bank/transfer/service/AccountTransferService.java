package com.bank.transfer.service;

import com.bank.transfer.entity.AccountTransfer;

import java.util.List;
import java.util.Optional;

public interface AccountTransferService {

    void save(AccountTransfer accountTransfer);

    void update(Long id, AccountTransfer accountTransfer);

    void deleteById(Long id);

    Optional<AccountTransfer> getById(Long id);

    Optional<AccountTransfer> getByAccountNumber(Long accountNumber);

    List<AccountTransfer> getAll();

}
