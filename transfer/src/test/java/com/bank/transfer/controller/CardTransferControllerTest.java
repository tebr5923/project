package com.bank.transfer.controller;

import com.bank.transfer.dto.transfer.CardTransferDTO;
import com.bank.transfer.dto.transfer.PatchCardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.exception.CardTransferNotFoundException;
import com.bank.transfer.exception.CardTransferValidationException;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.TransferService;
import com.bank.transfer.validator.CardTransferCardNumberUniqueValidator;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CardTransferControllerTest {
    private static final Long ID = 1L;

    private CardTransfer transfer;
    private CardTransfer transferToSave;
    private CardTransferDTO dto;
    private PatchCardTransferDTO patchDto;
    private List<CardTransfer> transfers;
    private List<CardTransferDTO> dtoList;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private TransferService<CardTransfer> transferService;

    @Mock
    private CardTransferCardNumberUniqueValidator validator;

    @Mock
    private AuditService auditService;

    @InjectMocks
    CardTransferController controller;


    @BeforeEach
    void init() {
        transfer = CardTransfer.builder()
                .id(ID)
                .amount(BigDecimal.valueOf(11.11))
                .purpose("test")
                .accountDetailsId(11L)
                .cardNumber(11L)
                .build();
        transferToSave = CardTransfer.builder()
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
        patchDto = PatchCardTransferDTO.builder()
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
        doReturn(transfers).when(transferService).getAll();

        var actual = controller.getAll();

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getById_shouldReturnValidResponseEntity_whenGetTransferWhichExist() {
        doReturn(Optional.of(transfer)).when(transferService).getById(ID);
        var expected = new ResponseEntity<>(dto, HttpStatus.OK);

        var actual = controller.getById(ID);

        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getById_shouldThrowCardTransferNotFoundException_whenGetTransferWhichNotExist() {
        doReturn(Optional.empty()).when(transferService).getById(ID);

        assertThatThrownBy(() -> controller.getById(ID))
                .isInstanceOf(CardTransferNotFoundException.class)
                .hasMessage(String.format("cardTransfer with id= %d not found", ID));
    }


    @Test
    void create_shouldReturnValidResponseEntity_whenCreatingTransferIsValid() {
        doReturn(false).when(bindingResult).hasErrors();
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
    void create_shouldThrowCardTransferValidationException_whenCreatingTransferIsNotValid() {
        doReturn(true).when(bindingResult).hasErrors();

        assertThatThrownBy(() -> controller.create(dto, bindingResult))
                .isInstanceOf(CardTransferValidationException.class);
    }


    @Test
    void update_shouldReturnValidResponseEntity_whenUpdatingTransferIsValid() {
        doReturn(Optional.of(transfer)).when(transferService).getById(ID);
        doReturn(false).when(bindingResult).hasErrors();
        var expected = new ResponseEntity<>(dto, HttpStatus.OK);

        var actual = controller.update(ID, dto, bindingResult);

        verify(validator, times(1)).validate(transfer, bindingResult);
        verify(transferService, times(1)).update(ID, transfer);
        verify(auditService, times(1)).save(any());
        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void update_shouldThrowCardTransferValidationException_whenUpdatingTransferIsNotValid() {
        doReturn(Optional.of(transfer)).when(transferService).getById(ID);
        doReturn(true).when(bindingResult).hasErrors();

        assertThatThrownBy(() -> controller.update(ID, dto, bindingResult))
                .isInstanceOf(CardTransferValidationException.class);
    }


    @Test
    void update_shouldThrowCardTransferNotFoundException_whenUpdateTransferWhichNotExist() {
        doReturn(Optional.empty()).when(transferService).getById(ID);

        assertThatThrownBy(() -> controller.update(ID, dto, bindingResult))
                .isInstanceOf(CardTransferNotFoundException.class)
                .hasMessage(String.format("cardTransfer with id= %d not found", ID));
    }


    @Test
    void patchUpdate_shouldReturnValidResponseEntity_whenUpdatingTransferIsValid() {
        doReturn(Optional.of(transfer)).when(transferService).getById(ID);
        doReturn(false).when(bindingResult).hasErrors();
        var expected = new ResponseEntity<>(patchDto, HttpStatus.OK);

        var actual = controller.patchUpdate(ID, patchDto, bindingResult);

        verify(validator, times(1)).validate(transfer, bindingResult);
        verify(transferService, times(1)).update(ID, transfer);
        assertThat(actual).isNotNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void patchUpdate_shouldThrowCardTransferValidationException_whenUpdatingTransferIsNotValid() {
        doReturn(Optional.of(transfer)).when(transferService).getById(ID);
        doReturn(true).when(bindingResult).hasErrors();

        assertThatThrownBy(() -> controller.patchUpdate(ID, patchDto, bindingResult))
                .isInstanceOf(CardTransferValidationException.class);
    }


    @Test
    void patchUpdate_shouldThrowCardTransferNotFoundException_whenUpdateTransferWhichNotExist() {
        doReturn(Optional.empty()).when(transferService).getById(ID);

        assertThatThrownBy(() -> controller.patchUpdate(ID, patchDto, bindingResult))
                .isInstanceOf(CardTransferNotFoundException.class)
                .hasMessage(String.format("cardTransfer with id= %d not found", ID));
    }


    @Test
    void delete_shouldCallDeleteFromService_whenDeleteTransferWhichExist() {
        doReturn(Optional.of(transfer)).when(transferService).getById(ID);

        controller.delete(ID);

        verify(transferService, times(1)).getById(ID);
        verify(transferService, times(1)).delete(ID);
        verify(auditService, times(1)).save(any());
    }


    @Test
    void delete_shouldThrowCardTransferNotFoundException_whenDeleteTransferWhichNotExist() {
        doReturn(Optional.empty()).when(transferService).getById(ID);

        assertThatThrownBy(() -> controller.delete(ID))
                .isInstanceOf(CardTransferNotFoundException.class)
                .hasMessage(String.format("cardTransfer with id= %d not found", ID));
    }

}