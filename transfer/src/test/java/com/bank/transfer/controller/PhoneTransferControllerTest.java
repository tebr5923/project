package com.bank.transfer.controller;

import com.bank.transfer.dto.transfer.CardTransferDTO;
import com.bank.transfer.dto.transfer.PhoneTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.TransferService;
import com.bank.transfer.validator.CardTransferCardNumberUniqueValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneTransferControllerTest {
    private static final Long ID = 1L;
    private static final Long PHONE_NUMBER = 123L;

    private static PhoneTransfer transfer;
    private static PhoneTransferDTO dto;
    private static List<PhoneTransfer> transfers;
    private static List<PhoneTransferDTO> dtoList;

    @Mock
    private TransferService<PhoneTransfer> transferService;

    @Mock
    private AuditService auditService;

    @InjectMocks
    PhoneTransferController controller;


    @BeforeAll
    static void init() {
        transfer = PhoneTransfer.builder()
                .id(ID)
                .amount(BigDecimal.valueOf(11.11))
                .purpose("test")
                .accountDetailsId(11L)
                .phoneNumber(11L)
                .build();
        dto = PhoneTransferDTO.builder()
                .amount(BigDecimal.valueOf(11.11))
                .purpose("test")
                .accountDetailsId(11L)
                .phoneNumber(11L)
                .build();
        transfers = List.of(transfer, new PhoneTransfer());
        dtoList = List.of(dto, new PhoneTransferDTO());
    }


    @Test
    void getAll_returnValidResponseEntity() {
        var expected = new ResponseEntity<>(dtoList, HttpStatus.OK);
        when(transferService.getAll()).thenReturn(transfers);

        var actual = controller.getAll();

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }
}