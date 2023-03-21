package com.bank.transfer.controller;

import com.bank.transfer.dto.transfer.AccountTransferDTO;
import com.bank.transfer.dto.transfer.PatchAccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.entity.Audit;
import com.bank.transfer.exception.AccountTransferValidationException;
import com.bank.transfer.exception.AccountTransferNotFoundException;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.mapper.PatchAccountTransferMapper;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.TransferService;
import com.bank.transfer.utils.Utils;
import com.bank.transfer.validator.AccountTransferAccountNumberUniqueValidator;
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
@RequestMapping("/account_transfers")
public class AccountTransferController {

    private final TransferService<AccountTransfer> accountTransferService;
    private final AccountTransferAccountNumberUniqueValidator validator;
    private final AuditService auditService;

    @Autowired
    public AccountTransferController(TransferService<AccountTransfer> accountTransferService, AccountTransferAccountNumberUniqueValidator validator, AuditService auditService) {
        this.accountTransferService = accountTransferService;
        this.validator = validator;
        this.auditService = auditService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountTransferDTO>> getAll() {
        var dtoList = accountTransferService.getAll()
                .stream()
                .map(AccountTransferMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransferDTO> getById(@PathVariable("id") Long id) {
        var accountTransfer =
                accountTransferService
                        .getById(id)
                        .orElseThrow(() -> new AccountTransferNotFoundException(String.format("accountTransfer with id= %d not found", id)));
        var dto = AccountTransferMapper.MAPPER.toDTO(accountTransfer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED) //201
    public ResponseEntity<AccountTransferDTO> create(@RequestBody @Valid AccountTransferDTO dto, BindingResult bindingResult) {
        var accountTransfer = AccountTransferMapper.MAPPER.toEntity(dto);

        validator.validate(accountTransfer, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new AccountTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        accountTransferService.save(accountTransfer);

        Audit audit = new Audit();
        audit.setEntityType("AccountTransfer");
        audit.setOperationType("SAVE");
        audit.setNewEntityJson(accountTransfer.toString());
        auditService.save(audit);

        var savedDto = AccountTransferMapper.MAPPER.toDTO(accountTransfer);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);//201
    }


    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransferDTO> update(@PathVariable("id") Long id,
                                                     @RequestBody @Valid AccountTransferDTO dto,
                                                     BindingResult bindingResult) {
        var oldTransfer = accountTransferService.getById(id)
                .orElseThrow(() -> new AccountTransferNotFoundException(String.format("accountTransfer with id= %d not found", id)));

        var accountTransfer = AccountTransferMapper.MAPPER.toEntity(dto);
        accountTransfer.setId(id);// for validation

        validator.validate(accountTransfer, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new AccountTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        accountTransferService.update(id, accountTransfer);

        Audit audit = new Audit();
        audit.setEntityType("AccountTransfer");
        audit.setOperationType("PUT_UPDATE");
        audit.setNewEntityJson(accountTransfer.toString());
        audit.setEntityJson(oldTransfer.toString());
        auditService.save(audit);

        var updatedDto = AccountTransferMapper.MAPPER.toDTO(accountTransfer);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }


    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatchAccountTransferDTO> patchUpdate(@PathVariable("id") Long id,
                                                          @RequestBody @Valid PatchAccountTransferDTO dto,
                                                          BindingResult bindingResult) {
        var accountTransferFromDB =
                accountTransferService.getById(id)
                        .orElseThrow(() -> new AccountTransferNotFoundException(String.format("accountTransfer with id= %d not found", id)));
        var accountTransfer = PatchAccountTransferMapper.MAPPER.toEntity(dto);
        accountTransfer.setId(id);// for validation

        validator.validate(accountTransfer, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new AccountTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        if (accountTransfer.getAmount() != null) {
            accountTransferFromDB.setAmount(accountTransfer.getAmount());
        }
        if (accountTransfer.getPurpose() != null) {
            accountTransferFromDB.setPurpose(accountTransfer.getPurpose());
        }
        if (accountTransfer.getAccountDetailsId() != null) {
            accountTransferFromDB.setAccountDetailsId(accountTransfer.getAccountDetailsId());
        }
        if (accountTransfer.getAccountNumber() != null) {
            accountTransferFromDB.setAccountNumber(accountTransfer.getAccountNumber());
        }
        accountTransferService.update(id, accountTransferFromDB);
        var updatedDto = PatchAccountTransferMapper.MAPPER.toDTO(accountTransferFromDB);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        var accountTransferFromDB =
                accountTransferService.getById(id)
                        .orElseThrow(() -> new AccountTransferNotFoundException(String.format("accountTransfer with id= %d not found", id)));

        accountTransferService.delete(id);

        Audit audit = new Audit();
        audit.setEntityType("AccountTransfer");
        audit.setOperationType("DELETE");
        audit.setNewEntityJson(accountTransferFromDB.toString());
        audit.setEntityJson(accountTransferFromDB.toString());
        auditService.save(audit);
    }

}
