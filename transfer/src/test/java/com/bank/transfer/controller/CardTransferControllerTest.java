package com.bank.transfer.controller;

import com.bank.transfer.dto.transfer.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
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
class CardTransferControllerTest {
    private static final Long ID = 1L;
    private static final Long CARD_NUMBER = 123L;

    private static CardTransfer transfer;
    private static CardTransferDTO dto;
    private static List<CardTransfer> transfers;
    private static List<CardTransferDTO> dtoList;


    @Mock
    private TransferService<CardTransfer> transferService;

    @Mock
    private CardTransferCardNumberUniqueValidator validator;

    @Mock
    private AuditService auditService;

    @InjectMocks
    CardTransferController controller;


    @BeforeAll
    static void init() {
        transfer = CardTransfer.builder()
                .id(ID)
                .amount(BigDecimal.valueOf(11.11))
                .purpose("test")
                .accountDetailsId(11L)
                .cardNumber(11L)
                .build();
        dto = CardTransferDTO.builder()
                .amount(BigDecimal.valueOf(11.11))
                .purpose("test")
                .accountDetailsId(11L)
                .cardNumber(11L)
                .build();
        transfers = List.of(transfer, new CardTransfer());
        dtoList = List.of(dto, new CardTransferDTO());
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