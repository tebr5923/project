package com.bank.profile.service.serviceInterface;

import com.bank.profile.entity.AccountDetailsId;

import java.util.List;

public interface AccountDetailsIdService {
    void saveAccountDetailsId(AccountDetailsId accountDetailsId);

    AccountDetailsId findAccountDetailsIdById(long accountDetailsIdId);

    void editAccountDetailsId(AccountDetailsId accountDetailsId);

    void deleteAccountDetailsId(long accountDetailsIdId);

    List<AccountDetailsId> getAllAccountDetailsId();
}
