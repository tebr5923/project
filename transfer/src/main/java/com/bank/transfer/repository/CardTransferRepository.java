package com.bank.transfer.repository;

import com.bank.transfer.entity.CardTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardTransferRepository extends JpaRepository<CardTransfer, Long> {

    Optional<CardTransfer> getByCardNumber(Long cardNumber);
}
