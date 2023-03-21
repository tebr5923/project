package com.bank.transfer.service;

import java.util.List;
import java.util.Optional;

public interface TransferService<T> {

    void save(T transfer);

    void update(Long id, T transfer);

    void delete(Long id);

    Optional<T> getById(Long id);

    Optional<T> getByNumber(Long number);

    List<T> getAll();

}
