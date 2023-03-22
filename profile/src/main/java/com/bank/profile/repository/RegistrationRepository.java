package com.bank.profile.repository;

import com.bank.profile.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
