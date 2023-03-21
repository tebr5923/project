package com.bank.transfer.controller;


import com.bank.transfer.dto.transfer.PatchPhoneTransferDTO;
import com.bank.transfer.dto.transfer.PhoneTransferDTO;
import com.bank.transfer.entity.Audit;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.exception.PhoneTransferNotFoundException;
import com.bank.transfer.exception.PhoneTransferValidationException;
import com.bank.transfer.mapper.PatchPhoneTransferMapper;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.TransferService;
import com.bank.transfer.utils.Utils;
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
@RequestMapping("/phone_transfers")
public class PhoneTransferController {

    private final TransferService<PhoneTransfer> transferService;
    private final AuditService auditService;


    @Autowired
    public PhoneTransferController(TransferService<PhoneTransfer> transferService, AuditService auditService) {
        this.transferService = transferService;
        this.auditService = auditService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneTransferDTO>> getAll() {
        var dtoList = transferService.getAll()
                .stream()
                .map(PhoneTransferMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneTransferDTO> getById(@PathVariable("id") Long id) {
        var transfer =
                transferService
                        .getById(id)
                        .orElseThrow(() -> new PhoneTransferNotFoundException(String.format("phoneTransfer with id= %d not found", id)));
        var dto = PhoneTransferMapper.MAPPER.toDTO(transfer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED) //201
    public ResponseEntity<PhoneTransferDTO> create(
            @RequestBody @Valid PhoneTransferDTO dto,
            BindingResult bindingResult) {
        var transfer = PhoneTransferMapper.MAPPER.toEntity(dto);

        if (bindingResult.hasErrors()) {
            throw new PhoneTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        transferService.save(transfer);

        Audit audit = new Audit();
        audit.setEntityType("PhoneTransfer");
        audit.setOperationType("SAVE");
        audit.setNewEntityJson(transfer.toString());
        auditService.save(audit);

        var savedDto = PhoneTransferMapper.MAPPER.toDTO(transfer);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);//201
    }


    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneTransferDTO> update(@PathVariable("id") Long id,
                                                   @RequestBody @Valid PhoneTransferDTO dto,
                                                   BindingResult bindingResult) {
        var oldTransfer = transferService.getById(id)
                .orElseThrow(() -> new PhoneTransferNotFoundException(String.format("phoneTransfer with id= %d not found", id)));

        var transfer = PhoneTransferMapper.MAPPER.toEntity(dto);

        if (bindingResult.hasErrors()) {
            throw new PhoneTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        transferService.update(id, transfer);

        Audit audit = new Audit();
        audit.setEntityType("PhoneTransfer");
        audit.setOperationType("PUT_UPDATE");
        audit.setNewEntityJson(transfer.toString());
        audit.setEntityJson(oldTransfer.toString());
        auditService.save(audit);

        var updatedDto = PhoneTransferMapper.MAPPER.toDTO(transfer);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }


    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatchPhoneTransferDTO> patchUpdate(@PathVariable("id") Long id,
                                                             @RequestBody @Valid PatchPhoneTransferDTO dto,
                                                             BindingResult bindingResult) {
        var transferFromDB =
                transferService.getById(id)
                        .orElseThrow(() -> new PhoneTransferNotFoundException(String.format("phoneTransfer with id= %d not found", id)));
        var transfer = PatchPhoneTransferMapper.MAPPER.toEntity(dto);

        if (bindingResult.hasErrors()) {
            throw new PhoneTransferValidationException(Utils.getErrorsMessage(bindingResult));
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
        if (transfer.getPhoneNumber() != null) {
            transferFromDB.setPhoneNumber(transfer.getPhoneNumber());
        }
        transferService.update(id, transferFromDB);
        var updatedDto = PatchPhoneTransferMapper.MAPPER.toDTO(transferFromDB);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        var transferFromDB =
                transferService.getById(id)
                        .orElseThrow(() -> new PhoneTransferNotFoundException(String.format("phoneTransfer with id= %d not found", id)));

        transferService.delete(id);

        Audit audit = new Audit();
        audit.setEntityType("PhoneTransfer");
        audit.setOperationType("DELETE");
        audit.setNewEntityJson(transferFromDB.toString());
        audit.setEntityJson(transferFromDB.toString());
        auditService.save(audit);
    }


}
