package com.bank.transfer.controller;

import com.bank.transfer.dto.transfer.CardTransferDTO;
import com.bank.transfer.dto.transfer.PatchCardTransferDTO;
import com.bank.transfer.entity.Audit;
import com.bank.transfer.entity.CardTransfer;
import com.bank.transfer.exception.CardTransferNotFoundException;
import com.bank.transfer.exception.CardTransferValidationException;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.mapper.PatchCardTransferMapper;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.TransferService;
import com.bank.transfer.utils.Utils;
import com.bank.transfer.validator.CardTransferCardNumberUniqueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/card_transfers")
public class CardTransferController {

    private final TransferService<CardTransfer> transferService;
    private final CardTransferCardNumberUniqueValidator validator;
    private final AuditService auditService;


    @Autowired
    public CardTransferController(TransferService<CardTransfer> transferService, CardTransferCardNumberUniqueValidator validator, AuditService auditService) {
        this.transferService = transferService;
        this.validator = validator;
        this.auditService = auditService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardTransferDTO>> getAll() {
        var dtoList = transferService.getAll()
                .stream()
                .map(CardTransferMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardTransferDTO> getById(@PathVariable("id") Long id) {
        var transfer =
                transferService
                        .getById(id)
                        .orElseThrow(() -> new CardTransferNotFoundException(String.format("cardTransfer with id= %d not found", id)));
        var dto = CardTransferMapper.MAPPER.toDTO(transfer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED) //201
    public ResponseEntity<CardTransferDTO> create(@RequestBody @Valid CardTransferDTO dto, BindingResult bindingResult) {
        var transfer = CardTransferMapper.MAPPER.toEntity(dto);

        validator.validate(transfer, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new CardTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        transferService.save(transfer);

        Audit audit = new Audit();
        audit.setEntityType("CardTransfer");
        audit.setOperationType("SAVE");
        audit.setNewEntityJson(transfer.toString());
        auditService.save(audit);

        var savedDto = CardTransferMapper.MAPPER.toDTO(transfer);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);//201
    }


    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardTransferDTO> update(@PathVariable("id") Long id,
                                                  @RequestBody @Valid CardTransferDTO dto,
                                                  BindingResult bindingResult) {
        var oldTransfer = transferService.getById(id)
                .orElseThrow(() -> new CardTransferNotFoundException(String.format("cardTransfer with id= %d not found", id)));

        var transfer = CardTransferMapper.MAPPER.toEntity(dto);
        transfer.setId(id);// for validation

        validator.validate(transfer, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new CardTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        transferService.update(id, transfer);

        Audit audit = new Audit();
        audit.setEntityType("CardTransfer");
        audit.setOperationType("PUT_UPDATE");
        audit.setNewEntityJson(transfer.toString());
        audit.setEntityJson(oldTransfer.toString());
        auditService.save(audit);

        var updatedDto = CardTransferMapper.MAPPER.toDTO(transfer);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }


    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatchCardTransferDTO> patchUpdate(@PathVariable("id") Long id,
                                                            @RequestBody @Valid PatchCardTransferDTO dto,
                                                            BindingResult bindingResult) {
        var transferFromDB =
                transferService.getById(id)
                        .orElseThrow(() -> new CardTransferNotFoundException(String.format("cardTransfer with id= %d not found", id)));
        var transfer = PatchCardTransferMapper.MAPPER.toEntity(dto);
        transfer.setId(id);// for validation

        validator.validate(transfer, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new CardTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        if (transfer.getAmount() != null) {
            transferFromDB.setAmount(transfer.getAmount());
        }
        if (transfer.getPurpose() != null) {
            transferFromDB.setPurpose(transfer.getPurpose());
        }
        if (transfer.getAccountDetailsId() != null) {
            transferFromDB.setAccountDetailsId(transfer.getAccountDetailsId());
        }
        if (transfer.getCardNumber() != null) {
            transferFromDB.setCardNumber(transfer.getCardNumber());
        }
        transferService.update(id, transferFromDB);
        var updatedDto = PatchCardTransferMapper.MAPPER.toDTO(transferFromDB);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        var oldTransfer = transferService.getById(id)
                .orElseThrow(() -> new CardTransferNotFoundException(String.format("cardTransfer with id= %d not found", id)));

        transferService.delete(id);

        Audit audit = new Audit();
        audit.setEntityType("CardTransfer");
        audit.setOperationType("DELETE");
        audit.setNewEntityJson(oldTransfer.toString());
        audit.setEntityJson(oldTransfer.toString());
        auditService.save(audit);
    }

}
