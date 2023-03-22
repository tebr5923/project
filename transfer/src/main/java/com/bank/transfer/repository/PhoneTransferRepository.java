package com.bank.transfer.repository;

import com.bank.transfer.entity.PhoneTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneTransferRepository extends JpaRepository<PhoneTransfer, Long> {

    Optional<PhoneTransfer> getByPhoneNumber(Long phoneNumber);
}
