package com.bank.transfer.controller;

import com.bank.transfer.dto.transfer.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.exception.AccountTransferNotFoundException;
import com.bank.transfer.exception.AccountTransferValidationException;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.TransferService;
import com.bank.transfer.validator.AccountTransferAccountNumberUniqueValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountTransferControllerTest {
    private static final Long ID = 1L;
    private static final Long ACCOUNT_NUMBER = 123L;

    private static AccountTransfer transfer;
    private static AccountTransfer transferToSave;
    private static AccountTransferDTO dto;
    private static List<AccountTransfer> transfers;
    private static List<AccountTransferDTO> dtoList;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private TransferService<AccountTransfer> transferService;

    @Mock
    private AccountTransferAccountNumberUniqueValidator validator;

    @Mock
    private AuditService auditService;

    @InjectMocks
    private AccountTransferController controller;

    @BeforeAll
    static void init() {
        transfer = AccountTransfer.builder()
                .id(ID)
                .amount(BigDecimal.valueOf(11.11))
                .purpose("test")
                .accountDetailsId(11L)
                .accountNumber(11L)
                .build();
        transferToSave = AccountTransfer.builder()
                .amount(BigDecimal.valueOf(11.11))
                .purpose("test")
                .accountDetailsId(11L)
                .accountNumber(11L)
                .build();
        dto = AccountTransferDTO.builder()
                .amount(BigDecimal.valueOf(11.11))
                .purpose("test")
                .accountDetailsId(11L)
                .accountNumber(11L)
                .build();
        transfers = List.of(transfer, new AccountTransfer());
        dtoList = List.of(dto, new AccountTransferDTO());
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


    @Test
    void getById_shouldReturnValidResponseEntity_whenGetTransferWhichExist() {
        when(transferService.getById(ID)).thenReturn(Optional.of(transfer));
        var expected = new ResponseEntity<>(dto, HttpStatus.OK);

        var actual = controller.getById(ID);

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getById_shouldThrowAccountTransferNotFoundException_whenGetTransferWhichNotExist() {
        when(transferService.getById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> controller.getById(ID))
                .isInstanceOf(AccountTransferNotFoundException.class)
                .hasMessage(String.format("accountTransfer with id= %d not found", ID));
    }


    @Test
    void create_shouldReturnValidResponseEntity_whenCreatingTransferIsValid() {
        when(bindingResult.hasErrors()).thenReturn(false);
        var expected = new ResponseEntity<>(dto, HttpStatus.CREATED);

        var actual = controller.create(dto, bindingResult);

        verify(validator, times(1)).validate(transferToSave, bindingResult);
        verify(transferService, times(1)).save(transferToSave);
        verify(auditService, times(1)).save(any());
        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void create_shouldThrowAccountTransferValidationException_whenCreatingTransferIsNotValid() {
        when(bindingResult.hasErrors()).thenReturn(true);

        assertThatThrownBy(() -> controller.create(dto, bindingResult))
                .isInstanceOf(AccountTransferValidationException.class);
    }

}