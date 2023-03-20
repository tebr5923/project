package com.bank.profile.repository;

import com.bank.profile.entity.AccountDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsIdRepository extends JpaRepository<AccountDetailsId, Long> {
}
