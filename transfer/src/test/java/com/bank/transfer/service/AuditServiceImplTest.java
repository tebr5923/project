package com.bank.transfer.service;

import com.bank.transfer.entity.Audit;
import com.bank.transfer.repository.AuditRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {
    private static final Long ID = 1L;

    private static Audit audit;
    private static List<Audit> audits;

    @Mock
    private AuditRepository repository;

    @InjectMocks
    private AuditServiceImpl service;


    @BeforeAll
    static void init() {
        audit = Audit.builder()
                .id(ID)
                .entityType("entityType")
                .operationType("operationType")
                .createdBy("createdBy")
                .modifiedBy("modifiedBy")
                .createdAt(OffsetDateTime.now())
                .modifiedAt(OffsetDateTime.now())
                .newEntityJson("newEntityJson")
                .entityJson("entityJson")
                .build();
        audits = List.of(new Audit(), new Audit());
    }


    @Test
    void getById_shouldCallFindByIdFromRepository_whenCallGetByIdFromService() {
        when(repository.findById(ID)).thenReturn(Optional.of(audit));
        var expected = Optional.of(audit);

        var actual = service.getById(ID);

        verify(repository, times(1)).findById(ID);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getAll_shouldCallFindAllFromFromRepository_whenCallGetAllFromService() {
        when(repository.findAll()).thenReturn(audits);
        var expected = audits;

        var actual = service.getAll();

        verify(repository, times(1)).findAll();
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void save_shouldCallSaveFromRepository_whenCallSaveFromService() {
        var audit = new Audit();

        service.save(audit);

        verify(repository, times(1)).save(audit);
    }

}