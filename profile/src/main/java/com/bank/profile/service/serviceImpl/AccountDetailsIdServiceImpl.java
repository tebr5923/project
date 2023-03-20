package com.bank.profile.service.serviceImpl;

import com.bank.profile.entity.AccountDetailsId;
import com.bank.profile.repository.AccountDetailsIdRepository;
import com.bank.profile.service.serviceInterface.AccountDetailsIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDetailsIdServiceImpl implements AccountDetailsIdService {
    private final AccountDetailsIdRepository accountDetailsIdRepository;

    @Autowired
    public AccountDetailsIdServiceImpl(AccountDetailsIdRepository accountDetailsIdRepository) {
        this.accountDetailsIdRepository = accountDetailsIdRepository;
    }

    @Override
    public void saveAccountDetailsId(AccountDetailsId accountDetailsId) {
        accountDetailsIdRepository.save(accountDetailsId);
    }

    @Override
    public AccountDetailsId findAccountDetailsIdById(long accountDetailsIdId) {
        return accountDetailsIdRepository.findById(accountDetailsIdId).orElseThrow(NullPointerException::new);
    }

    @Override
    public void editAccountDetailsId(Long id, AccountDetailsId accountDetailsId) {
        accountDetailsId.setId(id);
        accountDetailsIdRepository.save(accountDetailsId);
    }

    @Override
    public void deleteAccountDetailsId(long accountDetailsIdId) {
        accountDetailsIdRepository.deleteById(accountDetailsIdId);
    }

    @Override
    public List<AccountDetailsId> getAllAccountDetailsId() {
        return accountDetailsIdRepository.findAll();
    }
}
