package com.bank.transfer.service;

import com.bank.transfer.entity.Audit;
import com.bank.transfer.repository.AuditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    private static final Long ID = 1L;

    @Mock
    private AuditRepository repository;

    @InjectMocks
    private AuditServiceImpl service;

    @Test
    void getById_shouldCallFindByIdFromRepository_whenCallGetByIdFromService() {
        var audit = Audit.builder()
                .id(ID)
                .build();
        when(repository.findById(ID)).thenReturn(Optional.of(audit));
        var expected = Optional.of(audit);

        var actual = service.getById(ID);

        verify(repository, times(1)).findById(ID);
        assertEquals(expected, actual);
    }


    @Test
    void getAll_shouldCallFindAllFromFromRepository_whenCallGetAllFromService() {
        service.getAll();

        verify(repository, times(1)).findAll();
    }


    @Test
    void save_shouldCallSaveFromRepository_whenCallSaveFromService() {
        var audit = new Audit();

        service.save(audit);

        verify(repository, times(1)).save(audit);
    }

}