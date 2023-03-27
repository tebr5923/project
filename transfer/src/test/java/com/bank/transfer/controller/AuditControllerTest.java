package com.bank.transfer.controller;

import com.bank.transfer.dto.audit.AuditDto;
import com.bank.transfer.entity.Audit;
import com.bank.transfer.exception.AuditNotFoundException;
import com.bank.transfer.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AuditControllerTest {
    private static final Long ID = 1L;

    private Audit audit;
    private AuditDto dto;
    private List<Audit> audits;
    private List<AuditDto> dtoList;

    @Mock
    private AuditService auditService;

    @InjectMocks
    AuditController controller;


    @BeforeEach
    void init() {
        var createdAt = OffsetDateTime.now();
        var modifiedAt = OffsetDateTime.now();
        audit = Audit.builder()
                .id(ID)
                .entityType("entityType")
                .operationType("operationType")
                .createdBy("createdBy")
                .modifiedBy("modifiedBy")
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .newEntityJson("newEntityJson")
                .entityJson("entityJson")
                .build();
        dto = AuditDto.builder()
                .entityType("entityType")
                .operationType("operationType")
                .createdBy("createdBy")
                .modifiedBy("modifiedBy")
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .newEntityJson("newEntityJson")
                .entityJson("entityJson")
                .build();
        audits = List.of(audit, new Audit());
        dtoList = List.of(dto, new AuditDto());
    }


    @Test
    void getAll_returnValidResponseEntity() {
        var expected = new ResponseEntity<>(dtoList, HttpStatus.OK);
        doReturn(audits).when(auditService).getAll();

        var actual = controller.getAll();

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getById_shouldReturnValidResponseEntity_whenGetAuditWhichExist() {
        doReturn(Optional.of(audit)).when(auditService).getById(ID);
        var expected = new ResponseEntity<>(dto, HttpStatus.OK);

        var actual = controller.getById(ID);

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getById_shouldThrowAuditNotFoundException_whenGetAuditWhichNotExist() {
        doReturn(Optional.empty()).when(auditService).getById(ID);

        //  assertThrows(AuditNotFoundException.class, () -> controller.getById(ID));
        assertThatThrownBy(() -> controller.getById(ID))
                .isInstanceOf(AuditNotFoundException.class)
                .hasMessage(String.format("audit with id= %d not found", ID));
    }

}
