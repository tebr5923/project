package com.bank.transfer.repository;

import com.bank.transfer.entity.AccountTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTransferRepository extends JpaRepository<AccountTransfer, Long> {

    Optional<AccountTransfer> getByAccountNumber(Long accountNumber);
}
