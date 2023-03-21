package com.bank.transfer.controller;


import com.bank.transfer.dto.transfer.PatchPhoneTransferDTO;
import com.bank.transfer.dto.transfer.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import com.bank.transfer.exception.PhoneTransferNotFoundException;
import com.bank.transfer.exception.PhoneTransferValidationException;
import com.bank.transfer.mapper.PatchPhoneTransferMapper;
import com.bank.transfer.mapper.PhoneTransferMapper;
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

    @Autowired
    public PhoneTransferController(TransferService<PhoneTransfer> transferService) {
        this.transferService = transferService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneTransferDTO>> getAll() {
        var dtoList = transferService.getAll()
                .stream()
                .map(PhoneTransferMapper.MAPPER::ToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneTransferDTO> getById(@PathVariable("id") Long id) {
        var transfer =
                transferService
                        .getById(id)
                        .orElseThrow(() -> new PhoneTransferNotFoundException(String.format("phoneTransfer with id= %d not found", id)));
        var dto = PhoneTransferMapper.MAPPER.ToDTO(transfer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED) //201
    public ResponseEntity<PhoneTransferDTO> create(
            @RequestBody @Valid PhoneTransferDTO dto,
            BindingResult bindingResult) {
        var transfer = PhoneTransferMapper.MAPPER.ToEntity(dto);

        if (bindingResult.hasErrors()) {
            throw new PhoneTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        transferService.save(transfer);
        var savedDto = PhoneTransferMapper.MAPPER.ToDTO(transfer);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);//201
    }


    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneTransferDTO> update(@PathVariable("id") Long id,
                                                   @RequestBody @Valid PhoneTransferDTO dto,
                                                   BindingResult bindingResult) {
        transferService.getById(id)
                .orElseThrow(() -> new PhoneTransferNotFoundException(String.format("phoneTransfer with id= %d not found", id)));

        var transfer = PhoneTransferMapper.MAPPER.ToEntity(dto);

        if (bindingResult.hasErrors()) {
            throw new PhoneTransferValidationException(Utils.getErrorsMessage(bindingResult));
        }

        transferService.update(id, transfer);
        var updatedDto = PhoneTransferMapper.MAPPER.ToDTO(transfer);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }


    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatchPhoneTransferDTO> patchUpdate(@PathVariable("id") Long id,
                                                             @RequestBody @Valid PatchPhoneTransferDTO dto,
                                                             BindingResult bindingResult) {
        var transferFromDB =
                transferService.getById(id)
                        .orElseThrow(() -> new PhoneTransferNotFoundException(String.format("phoneTransfer with id= %d not found", id)));
        var transfer = PatchPhoneTransferMapper.MAPPER.ToEntity(dto);

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
        var updatedDto = PatchPhoneTransferMapper.MAPPER.ToDTO(transferFromDB);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        transferService.deleteById(id);
    }


}
